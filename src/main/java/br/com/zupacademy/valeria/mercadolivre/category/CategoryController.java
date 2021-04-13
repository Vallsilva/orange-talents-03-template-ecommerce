package br.com.zupacademy.valeria.mercadolivre.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryRequest request){
        CategoryModel model = request.toModel();
        repository.save(model);

        Optional<CategoryModel> subCategory = Optional.empty();
        if(request.hasSubCategory()){
            subCategory = repository.findById(model.getSubCategory());
        }

        CategoryResponse response = new CategoryResponse(model, subCategory);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
