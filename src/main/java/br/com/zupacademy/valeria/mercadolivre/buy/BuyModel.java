package br.com.zupacademy.valeria.mercadolivre.buy;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class BuyModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private ProductModel productModel;
    @NotNull
    @ManyToOne
    private UserModel buyer;
    @NotNull
    @Positive
    private Long quantityItems;
    @NotNull
    @ManyToOne
    private UserModel seller;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentFormat paymentFormat;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BuyStatus status;

    public BuyModel() {
    }

    public BuyModel(@NotNull ProductModel productModel, @NotNull UserModel buyer, @NotNull @Positive Long quantityItems, @NotNull UserModel seller,
                    @NotNull PaymentFormat paymentFormat, @NotNull BuyStatus status) {

        this.productModel = productModel;
        this.buyer = buyer;
        this.quantityItems = quantityItems;
        this.seller = seller;
        this.paymentFormat = paymentFormat;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPaymentFormatUrl(){
        return paymentFormat.getUrl();
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public UserModel getBuyer() {
        return buyer;
    }

    public Long getQuantityItems() {
        return quantityItems;
    }

    public UserModel getSeller() {
        return seller;
    }

    public PaymentFormat getPaymentFormat() {
        return paymentFormat;
    }

    public BuyStatus getStatus() {
        return status;
    }

    public void setStatus(BuyStatus status) throws Exception {
        if(BuyStatus.COMPLETED.equals(status))
            throw new Exception("Changing a completed buy is not allowed!");
        this.status = status;
    }
}
