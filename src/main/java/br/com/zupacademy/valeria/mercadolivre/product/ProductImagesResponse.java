package br.com.zupacademy.valeria.mercadolivre.product;

import java.util.List;

public class ProductImagesResponse {

    private Long idProduct;
    private List<String> imagesUrl;


    public ProductImagesResponse(ProductModel model) {
        this.idProduct = model.getId();
        this.imagesUrl = model.getImagesUrl();
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }
}
