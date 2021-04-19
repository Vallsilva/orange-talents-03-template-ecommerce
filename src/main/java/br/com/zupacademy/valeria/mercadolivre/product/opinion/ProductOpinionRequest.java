package br.com.zupacademy.valeria.mercadolivre.product.opinion;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class ProductOpinionRequest {

    @NotNull @Range(min = 1, max = 5)
    private int rating;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    public ProductOpinionRequest(int note, String title, String description) {
        this.rating = note;
        this.title = title;
        this.description = description;
    }

    public ProductOpinionModel toModel(ProductModel productModel, UserModel loggedUser) {
        return new ProductOpinionModel(this.rating, this.title, this.description, loggedUser, productModel);
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
