package br.com.zupacademy.valeria.mercadolivre.product;

import br.com.zupacademy.valeria.mercadolivre.category.CategoryModel;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


public class ProductRequest {

    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Long amount;
    @NotNull
    private List<DetailsProduct> detailsProduct;
    @NotBlank @Size(max = 1000)
    private String descriptionProduct;
    @NotNull
    private Long idCategoryModel;


    public ProductRequest(@NotBlank String name, @NotNull BigDecimal price, @NotNull Long amount, @NotNull  List<DetailsProduct> detailsProduct,
                          @NotBlank @Length(max = 1000) String descriptionProduct, @NotNull Long idCategoryModel, UserModel owner) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.detailsProduct = detailsProduct;
        this.descriptionProduct = descriptionProduct;
        this.idCategoryModel = idCategoryModel;

    }

    public ProductModel toModel(List<DetailsProduct> details, CategoryModel category, UserModel user){

        return new ProductModel(this.name, this.price, this.amount, details, this.descriptionProduct, category, user);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAmount() {
        return amount;
    }

    public List<DetailsProduct> getDetailsProduct() {
        return detailsProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public Long getIdCategoryModel() {
        return idCategoryModel;
    }
}
