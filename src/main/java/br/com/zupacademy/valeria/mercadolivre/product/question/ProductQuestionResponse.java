package br.com.zupacademy.valeria.mercadolivre.product.question;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

public class ProductQuestionResponse {

    private String title;
    private UserModel userModel;
    private ProductModel productModel;

    public ProductQuestionResponse(ProductQuestionModel questionModel) {
        this.title = questionModel.getTitle();
        this.userModel = questionModel.getUser();
        this.productModel = questionModel.getProductModel();
    }

    public String getTitle() {
        return title;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }
}
