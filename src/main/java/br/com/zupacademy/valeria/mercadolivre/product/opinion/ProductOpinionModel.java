package br.com.zupacademy.valeria.mercadolivre.product.opinion;

import br.com.zupacademy.valeria.mercadolivre.product.ProductModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

import javax.persistence.*;

@Entity
public class ProductOpinionModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    private String title;
    private String description;
    @ManyToOne
    private UserModel loggedUser;
    @ManyToOne
    private ProductModel productModel;

    public ProductOpinionModel() {
    }

    public ProductOpinionModel(int note, String title, String description, UserModel loggedUser, ProductModel productModel) {
        this.note = note;
        this.title = title;
        this.description = description;
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
}
