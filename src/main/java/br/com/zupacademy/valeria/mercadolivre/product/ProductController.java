package br.com.zupacademy.valeria.mercadolivre.product;


import br.com.zupacademy.valeria.mercadolivre.category.CategoryModel;
import br.com.zupacademy.valeria.mercadolivre.category.CategoryRepository;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProductRepository;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserRepository;
import io.swagger.annotations.Api;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.BindException;
import java.security.Principal;
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

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ProductRequest request, Principal principal) {

        UserModel loggedUser = userRepository.getByLogin(principal.getName());
        CategoryModel category = categoryRepository.findById(request.getIdCategoryModel()).get();

        List<DetailsProduct> details = request.getDetailsProduct().stream().map(detailsProductRepository::save).collect(Collectors.toList());

        ProductModel model = request.toModel(details, category, loggedUser);
        if (model.isOwner(loggedUser)) {
            repository.save(model);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{productId}/upload")
    public ResponseEntity<?> uploadImage(@RequestPart(name = "image")MultipartFile image, @PathVariable Long productId,
                                         Principal principal) throws Exception{

        UserModel loggedUser = userRepository.findByLogin(principal.getName()).orElseThrow(BindException::new);
        ProductModel product = repository.findById(productId).orElseThrow(BadHttpRequest::new);

        String imageUrl = UploadImages.upload(image);

        if(product.isOwner(loggedUser)){
            product.addImage(imageUrl);
            ProductImagesResponse responseImage = new ProductImagesResponse(product);
            return ResponseEntity.ok(responseImage);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
