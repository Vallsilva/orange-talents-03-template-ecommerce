package br.com.zupacademy.valeria.mercadolivre.buy;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BuyRequest {

    @NotNull @Positive
    private Long quantity;
    @NotNull
    private PaymentFormat format;

    public BuyRequest(@NotNull @Positive Long quantity, @NotNull PaymentFormat format) {
        this.quantity = quantity;
        this.format = format;
    }

    public BuyModel toModel(ProductModel productModel, UserModel buyer, UserModel seller){
        return new BuyModel(productModel, buyer, quantity, seller, format, BuyStatus.STARTED);
    }

    public Long getQuantity() {
        return quantity;
    }

    public PaymentFormat getFormat() {
        return format;
    }
}
