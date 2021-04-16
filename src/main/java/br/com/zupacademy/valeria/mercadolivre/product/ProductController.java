package br.com.zupacademy.valeria.mercadolivre.product;


import br.com.zupacademy.valeria.mercadolivre.category.CategoryModel;
import br.com.zupacademy.valeria.mercadolivre.category.CategoryRepository;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProductRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DetailsProductRepository detailsProductRepository;

    @Transactional
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ProductRequest request){
        CategoryModel category = categoryRepository.findById(request.getIdCategoryModel()).get();


        List<DetailsProduct> details = request.getDetailsProduct().stream().map(detailsProductRepository::save).collect(Collectors.toList());


        ProductModel model = request.toModel(details, category);
        repository.save(model);

        return ResponseEntity.ok(model);
    }

}
