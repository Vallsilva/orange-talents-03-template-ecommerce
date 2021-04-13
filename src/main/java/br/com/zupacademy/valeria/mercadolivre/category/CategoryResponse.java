package br.com.zupacademy.valeria.mercadolivre.category;

import java.util.Optional;

public class CategoryResponse {

    private Long id;
    private String name;
    private String subCategory;

    public CategoryResponse(CategoryModel name, Optional<CategoryModel> subCategory) {
        this.id = name.getId();
        this.name = name.getName();
        subCategory.ifPresent(subCat -> this.subCategory = subCat.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubCategory() {
        return subCategory;
    }
}
