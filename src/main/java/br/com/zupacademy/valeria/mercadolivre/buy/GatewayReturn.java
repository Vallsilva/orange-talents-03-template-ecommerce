package br.com.zupacademy.valeria.mercadolivre.buy;

import javax.validation.constraints.NotNull;

public class GatewayReturn {

    @NotNull
    private Long paymentId;

    @NotNull
    private String status;

    public GatewayReturn(@NotNull Long paymentId, @NotNull String status) {
        this.paymentId = paymentId;
        this.status = status;
    }

    public GatewayTransaction toModel(BuyModel buyModel){
        return new GatewayTransaction(buyModel, paymentId, status);
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }
}
