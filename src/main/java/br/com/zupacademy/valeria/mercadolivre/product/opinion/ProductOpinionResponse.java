package br.com.zupacademy.valeria.mercadolivre.product.opinion;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

public class ProductOpinionResponse {

    private int note;
    private String title;
    private String description;
    private UserModel loggedUser;
    private ProductModel productModel;

    public ProductOpinionResponse(ProductOpinionModel opinionModel, UserModel loggedUser, ProductModel productModel) {
        this.note = opinionModel.getNote();
        this.title = opinionModel.getTitle();
        this.description = opinionModel.getDescription();
        this.loggedUser = loggedUser;
        this.productModel = productModel;
    }

    public int getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserModel getLoggedUser() {
        return loggedUser;
    }

    public ProductModel getProductModel() {
        return productModel;
    }
}
