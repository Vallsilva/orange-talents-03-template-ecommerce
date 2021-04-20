package br.com.zupacademy.valeria.mercadolivre.product.question;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductQuestionRequest {

    @NotBlank
    private String title;
    @NotNull
    private Long idUser;


    public ProductQuestionRequest(@NotBlank String title, @NotNull Long idUser) {
        this.title = title;
        this.idUser = idUser;
    }

    public ProductQuestionModel toModel(ProductModel productModel, UserModel loggedUser){
        return new ProductQuestionModel(this.title, loggedUser, productModel);
    }

    public String getTitle() {
        return title;
    }

    public Long getIdUser() {
        return idUser;
    }


}
