package br.com.zupacademy.valeria.mercadolivre.buy;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;

public class BuyResponse {

    private Long buyId;
    private String productName;
    private Long quantity;
    private String buyerEmail;
    private String sellerEmail;
    private String redirectUrl;

    public BuyResponse(BuyModel buyModel, String redirectUrl) {
        this.buyId = buyModel.getId();
        this.productName = buyModel.getProductModel().getName();
        this.quantity = buyModel.getQuantityItems();
        this.buyerEmail = buyModel.getBuyer().getLogin();
        this.sellerEmail = buyModel.getSeller().getLogin();
        this.redirectUrl = redirectUrl;
    }

    public Long getBuyId() {
        return buyId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
