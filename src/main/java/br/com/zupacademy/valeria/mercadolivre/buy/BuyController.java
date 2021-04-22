package br.com.zupacademy.valeria.mercadolivre.buy;

import br.com.zupacademy.valeria.mercadolivre.config.validator.MailSender;
import br.com.zupacademy.valeria.mercadolivre.config.validator.ValidatorErrorsDTO;
import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.product.ProductRepository;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpinionModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.net.URI;
import java.security.Principal;

@Api
@RestController
@RequestMapping("/acquisition")
public class BuyController {

    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{productId}")
    public ResponseEntity<?> acquisition(@PathVariable Long productId, @Valid @RequestBody BuyRequest buyRequest, Principal principal) throws Exception{

        ProductModel productModel = productRepository.findById(productId).orElseThrow(BindException::new);

        UserModel loggedUser = userRepository.getByLogin(principal.getName());

        if (!productModel.hasStock(buyRequest.getQuantity())){
            var validationDTO = new ValidatorErrorsDTO();
            validationDTO.addError("The quantity is greater than stock");
            return ResponseEntity.badRequest().body(validationDTO);
        }

        BuyModel buyModel = buyRequest.toModel(productModel, loggedUser, productModel.getOwner());

        productModel.descrementStock(buyModel.getQuantityItems());
        productRepository.save(productModel);

        BuyModel savedBuyModel = buyRepository.save(buyModel);

        var sender = new MailSender().setFrom(loggedUser.getLogin())
                .setTo("orange@mail.com").setSubject("[STARTED] Nova Compra - " + productModel.getName())
                .setText(String.format("Foi iniciada uma compra do produto %s - QTDE. %d - pelo usuário %s",
                        productModel.getName(), buyModel.getQuantityItems(), loggedUser.getLogin()))
                .send();

        var sucessUrl = new URI(String.format("/gateway/%s/buyModel/%d/return", savedBuyModel.getPaymentFormat(), buyModel.getId()));
        var redirectGatewayUrl = new URI(String.format(
                "%s?buyId=%d&redirectUrl=%s",
                buyModel.getPaymentFormatUrl(),
                buyModel.getId(),
                sucessUrl));

        HttpHeaders responseHeadres = new HttpHeaders();
        responseHeadres.setLocation(redirectGatewayUrl);

        BuyResponse response = new BuyResponse(savedBuyModel, redirectGatewayUrl.toString());

        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .headers(responseHeadres)
                .body(response);
    }
}
