package br.com.zupacademy.valeria.mercadolivre.product.question;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ProductQuestionModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate createAt;
    @ManyToOne
    private UserModel user;
    @ManyToOne
    private ProductModel productModel;

    public ProductQuestionModel() {
    }

    public ProductQuestionModel(String title, UserModel user, ProductModel productModel) {
        this.title = title;
        this.createAt = LocalDate.now();
        this.user = user;
        this.productModel = productModel;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public UserModel getUser() {
        return user;
    }

    public ProductModel getProductModel() {
        return productModel;
    }
}
