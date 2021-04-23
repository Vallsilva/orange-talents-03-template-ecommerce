package br.com.zupacademy.valeria.mercadolivre.buy;

import br.com.zupacademy.valeria.mercadolivre.config.validator.MailSender;
import br.com.zupacademy.valeria.mercadolivre.config.validator.ValidatorErrorsDTO;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserRepository;
import io.swagger.annotations.Api;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.logging.Logger;

@Api
@RestController
public class PaypalReturnController {

    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private GatewayTransactionRepository gatewayTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/gateway/paypal/productBuy/{buyId}/return")
    public ResponseEntity<?> productPurchasedReturn(@PathVariable Long buyId, @RequestBody @Valid GatewayReturn gatewayResponse,
                                                    PaypalGeneralStatusChecker paypalPaymentStatus, Principal principal) throws Exception {
        UserModel loggedUser = userRepository.findByLogin(principal.getName()).orElseThrow(BadHttpRequest::new);
        BuyModel productBuy = buyRepository.findById(buyId).orElseThrow(BadHttpRequest::new);
        Optional<GatewayTransaction> existingTransaction = gatewayTransactionRepository.findTopByPaymentIdOrderByCreatedAtDesc(gatewayResponse.getPaymentId());

        if(existingTransaction.isPresent()) {
            GatewayTransaction foundTransaction = existingTransaction.get();

            if(foundTransaction.isGatewaySuccess(paypalPaymentStatus)) {
                var validationDTO = new ValidatorErrorsDTO();
                validationDTO.addError("Changing a successful transaction is not allowed!");
                return ResponseEntity.badRequest().body(validationDTO);
            }

            GatewayTransaction newTransaction = foundTransaction.toNewTransaction(gatewayResponse.getStatus());
            return checkTransactionStatusAndExecute(newTransaction, paypalPaymentStatus, loggedUser);
        }

        GatewayTransaction newTransaction = gatewayResponse.toModel(productBuy);
        return checkTransactionStatusAndExecute(newTransaction, paypalPaymentStatus, loggedUser);
    }

    private ResponseEntity<?> checkTransactionStatusAndExecute(GatewayTransaction transaction, GatewayStatusChecker checker, UserModel loggedUser) throws Exception {

        BuyModel productBuy = transaction.getProductBuy();

        if(transaction.isGatewaySuccess(checker)) {
            gatewayTransactionRepository.save(transaction);

            productBuy.setStatus(BuyStatus.PAID);
            buyRepository.save(productBuy);

            invoice(productBuy.getId(), transaction.getId());
            ranking(productBuy.getId(), transaction.getProductBuy().getSeller().getId());
            sendSuccessMail(loggedUser, transaction);

            return ResponseEntity.ok(new NewGatewayTransactionResponse(transaction));
        }

        if(transaction.isGatewayError(checker)) {
            gatewayTransactionRepository.save(transaction);

            productBuy.setStatus(BuyStatus.PENDING);
            buyRepository.save(productBuy);

            sendErrorMail(loggedUser, transaction);

            return ResponseEntity.ok(new NewGatewayTransactionResponse(transaction));
        }

        return ResponseEntity.badRequest().build();
    }

    private void sendSuccessMail(UserModel loggedUser, GatewayTransaction transaction) {
        var sender = new MailSender()
                .setFrom(loggedUser.getLogin())
                .setTo("orange@gmail.com")
                .setSubject("Compra finalizada com sucesso!")
                .setText(String.format("A sua compra no %s do produto %s foi aprovada!",
                        transaction.getPaymentMethodName(), transaction.getProductName()))
                .send();
    }

    private void sendErrorMail(UserModel loggedUser, GatewayTransaction transaction) {
        var returnUrl = String.format("/gateway/%s/productBuy/%s/return",
                transaction.getPaymentMethodCode(), transaction.getProductBuy().getId());

        var tryAgainUrl = String.format("%s?buyId=%s&redirectUrl=%s",
                transaction.getPaymentFormat().getUrl(), transaction.getBuyModelId(), returnUrl);

        var sender = new MailSender()
                .setFrom(loggedUser.getLogin())
                .setTo("orange@gmail.com")
                .setSubject("Problema no pagamento da compra!")
                .setText(String.format("A sua compra no %s do produto %s não foi aprovada! Tente novamente: %s",
                        transaction.getPaymentMethodName(), transaction.getProductName(), tryAgainUrl))
                .send();
    }

    private void invoice(Long buyId, Long buyerId) {
        var logger = Logger.getLogger("global");
        logger.info("Enviando dados para nota fiscal...");
    }

    private void ranking(Long buyId, Long sellerId) {
        var logger = Logger.getLogger("global");
        logger.info("Enviando dados para ranking...");
    }
}
