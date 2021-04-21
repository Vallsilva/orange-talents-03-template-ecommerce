package br.com.zupacademy.valeria.mercadolivre.product;


import br.com.zupacademy.valeria.mercadolivre.category.CategoryModel;
import br.com.zupacademy.valeria.mercadolivre.category.CategoryRepository;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProduct;
import br.com.zupacademy.valeria.mercadolivre.product.details.DetailsProductRepository;
import br.com.zupacademy.valeria.mercadolivre.product.images.ProductImagesResponse;
import br.com.zupacademy.valeria.mercadolivre.product.images.UploadImages;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpinionModel;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpinionRequest;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpinionResponse;
import br.com.zupacademy.valeria.mercadolivre.product.opinion.ProductOpnionRepository;
import br.com.zupacademy.valeria.mercadolivre.product.question.*;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserRepository;
import io.swagger.annotations.Api;
import javassist.tools.web.BadHttpRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.BindException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ProductOpnionRepository opnionRepository;
    @Autowired
    private ProductQuestionRepository questionRepository;

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
    public ResponseEntity<?> uploadImage(@RequestPart(name = "image") MultipartFile image, @PathVariable Long productId,
                                         Principal principal) throws Exception {

        UserModel loggedUser = userRepository.findByLogin(principal.getName()).orElseThrow(BindException::new);
        ProductModel product = repository.findById(productId).orElseThrow(BadHttpRequest::new);

        String imageUrl = UploadImages.upload(image);

        if (product.isOwner(loggedUser)) {
            product.addImage(imageUrl);
            ProductImagesResponse responseImage = new ProductImagesResponse(product);
            return ResponseEntity.ok(responseImage);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/{idProduct}/opinion")
    public ResponseEntity<?> opnionProduct(@Valid @RequestBody ProductOpinionRequest opinionRequest, @PathVariable Long idProduct, Principal principal) {

        UserModel loggedUser = userRepository.getByLogin(principal.getName());
        ProductModel productModel = repository.findById(idProduct).get();
        ProductOpinionModel opinionModel = opinionRequest.toModel(productModel, loggedUser);
        if (!productModel.isOwner(loggedUser)) {
            opnionRepository.save(opinionModel);

            ProductOpinionResponse opinionResponse = new ProductOpinionResponse(opinionModel, loggedUser, productModel);
            return ResponseEntity.ok(opinionResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{idProduct}/question")
    public ResponseEntity<?> questionProduct(@Valid @RequestBody ProductQuestionRequest questionRequest, @PathVariable Long idProduct, Principal principal) {
        ProductModel productModel = repository.findById(idProduct).get();
        UserModel loggedUser = userRepository.getByLogin(principal.getName());

        ProductQuestionModel questionModel = questionRequest.toModel(productModel, loggedUser);

        questionRepository.save(questionModel);

        SendEmail sendEmail = new SendEmail();
        sendEmail.sendEmail();

        ProductQuestionResponse questionResponse = new ProductQuestionResponse(questionModel);
        return ResponseEntity.ok(questionResponse);


    }

    @GetMapping("/{idProduct}/search")
    public ResponseEntity<?> search(@PathVariable Long idProduct){

        ProductModel productModel = repository.findById(idProduct).get();

        List<String> questions = questionRepository.getQuestions(idProduct);

        Optional<ProductOpinionModel> opinionModel = opnionRepository.findById(idProduct);

        int totalRatings = opnionRepository.getTotal(idProduct);
        Double averageRatings = opnionRepository.getMedia(idProduct);

        ProductInfoResponse infoResponse = new ProductInfoResponse(productModel, averageRatings, totalRatings, opinionModel, questions);

        return ResponseEntity.ok(infoResponse);

    }
}

