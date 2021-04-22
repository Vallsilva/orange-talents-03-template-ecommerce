package br.com.zupacademy.valeria.mercadolivre.product;

import br.com.zupacademy.valeria.mercadolivre.category.CategoryModel;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Long amount;

    @OneToMany
    private List<DetailsProduct> detailsProduct;
    private String descriptionProduct;
    @ManyToOne
    private CategoryModel categoryModel;
    @PastOrPresent
    private LocalDate createdAt;
    @NotNull
    @ManyToOne
    private UserModel owner;

    @ElementCollection
    private List<String> imagesUrl = new ArrayList<>();

    public ProductModel(String name, BigDecimal price, Long amount, List<DetailsProduct> detailsProduct, String descriptionProduct,
                        CategoryModel categoryModel, UserModel owner) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.detailsProduct = detailsProduct;
        this.descriptionProduct = descriptionProduct;
        this.categoryModel = categoryModel;
        this.createdAt = LocalDate.now();
        this.owner = owner;
    }

    public ProductModel() {
    }

    public boolean hasStock(Long quantityPurchased){
        return this.amount >= quantityPurchased;

    }

    public UserModel getOwner() {
        return owner;
    }

    public boolean isOwner(UserModel user){
        return user.equals(this.owner);
    }

    public void addImage(String image){
       imagesUrl.add(image);
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<DetailsProduct> getDetailsProduct() {
        return detailsProduct;
    }

    public void setDetailsProduct(List<DetailsProduct> detailsProduct) {
        this.detailsProduct = detailsProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void descrementStock(Long quantityItems) {
        if (hasStock(quantityItems)){
            this.amount = this.amount - quantityItems;
        }
    }
}
