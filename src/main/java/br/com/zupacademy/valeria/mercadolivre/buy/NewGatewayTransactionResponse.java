package br.com.zupacademy.valeria.mercadolivre.buy;

public class NewGatewayTransactionResponse {
    private Long paymentId;
    private String paymentMethodName;
    private String productName;
    private String gatewayStatus;
    private BuyStatus buyStatus;

    public NewGatewayTransactionResponse(GatewayTransaction newTransaction) {
        this.paymentId = newTransaction.getPaymentId();
        this.paymentMethodName = newTransaction.getPaymentMethodName();
        this.productName = newTransaction.getProductName();
        this.gatewayStatus = newTransaction.getStatus();
        this.buyStatus = newTransaction.getProductBuy().getStatus();
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public String getProductName() {
        return productName;
    }

    public String getGatewayStatus() {
        return gatewayStatus;
    }

    public BuyStatus getBuyStatus() {
        return buyStatus;
    }
}
