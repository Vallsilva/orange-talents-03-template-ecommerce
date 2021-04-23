package br.com.zupacademy.valeria.mercadolivre.buy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class GatewayTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BuyModel buyModel;
    @NotNull
    private Long paymentId;
    @NotNull
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public GatewayTransaction() {
    }

    public GatewayTransaction(BuyModel buyModel, @NotNull Long paymentId, @NotNull String status) {
        this.buyModel = buyModel;
        this.paymentId = paymentId;
        this.status = status;
    }

    boolean isGatewaySuccess(GatewayStatusChecker checker) {
        return checker.isGatewaySuccess(status);
    }

    boolean isGatewayError(GatewayStatusChecker checker) {
        return checker.isGatewayError(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuyModel getProductBuy() {
        return buyModel;
    }

    public Long getBuyModelId() {
        return buyModel.getId();
    }

    public BuyStatus getBuyStatus() {
        return buyModel.getStatus();
    }

    public PaymentFormat getPaymentFormat() {
        return buyModel.getPaymentFormat();
    }

    public String getPaymentMethodName() {
        return buyModel.getPaymentFormat().getName();
    }

    public String getPaymentFormatCode() {
        return buyModel.getPaymentFormat().getCode();
    }

    public String getPaymentMethodCode() {
        return buyModel.getPaymentFormatUrl();
    }

    public String getProductName() {
        return buyModel.getProductModel().getName();
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws Exception {
        if(this.status.equals("SUCCESS")) throw new Exception("Changing a successful transaction is not allowed!");
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public GatewayTransaction toNewTransaction(String status) {
        return new GatewayTransaction(buyModel, paymentId, status);
    }
}
