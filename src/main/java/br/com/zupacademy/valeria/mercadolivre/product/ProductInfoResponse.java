package br.com.zupacademy.valeria.mercadolivre.product;

import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpinionModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductInfoResponse {

    private List<String> images;
    private String nameProduct;
    private BigDecimal price;
    private List<DetailsProduct> details;
    private String description;
    private Double averageRatings;
    private int totalRatings;
    private Optional<ProductOpinionModel> opinionProduct;
    private List<String> questionsProduct;

    public ProductInfoResponse(ProductModel productModel, Double averageRatings, int totalRatings, Optional<ProductOpinionModel> opinionProduct, List<String> questionsProduct) {
        this.images = productModel.getImagesUrl();
        this.nameProduct = productModel.getName();
        this.price = productModel.getPrice();
        this.details = productModel.getDetailsProduct();
        this.description = productModel.getDescriptionProduct();
        this.averageRatings = averageRatings;
        this.totalRatings = totalRatings;
        this.opinionProduct = opinionProduct;
        this.questionsProduct = questionsProduct;
    }

    public List<String> getImages() {
        return images;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<DetailsProduct> getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public Double getAverageRatings() {
        return averageRatings;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public Optional<ProductOpinionModel> getOpinionProduct() {
        return opinionProduct;
    }

    public List<String> getQuestionsProduct() {
        return questionsProduct;
    }
}
