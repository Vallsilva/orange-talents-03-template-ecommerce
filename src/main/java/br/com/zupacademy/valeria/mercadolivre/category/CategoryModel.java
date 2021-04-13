package br.com.zupacademy.valeria.mercadolivre.category;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class CategoryModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long subCategory;


    public CategoryModel() {

    }

    public CategoryModel(String name, Optional<Long> subCategory){
        this.name = name;
        this.subCategory = subCategory.orElse(null);
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

    public void setSubCategory(Long subCategory) {
        this.subCategory = subCategory;
    }

    public Long getSubCategory() {
        return subCategory;
    }
}
