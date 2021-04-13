package br.com.zupacademy.valeria.mercadolivre.category;

import br.com.zupacademy.valeria.mercadolivre.config.validator.UniqueValue;

import javax.validation.constraints.NotBlank;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class CategoryRequest {

    @NotBlank @UniqueValue(domainClass = CategoryModel.class, fieldName = "name")
    private String name;

    private Long idSubCategory;


    public CategoryModel toModel(){
        return new CategoryModel(name, Optional.ofNullable(idSubCategory));
    }

    public boolean hasSubCategory() {
        return nonNull(idSubCategory) && idSubCategory != 0;
    }

    public String getName() {
        return name;
    }

    public Long getIdSubCategory() {
        return idSubCategory;
    }


}
