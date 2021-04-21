package br.com.zupacademy.valeria.mercadolivre.product.opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOpnionRepository extends JpaRepository<ProductOpinionModel, Long> {


    @Query("SELECT COUNT(*) FROM ProductOpinionModel WHERE productModel.id = ?1")
    int getTotal(Long productOp);


    @Query("SELECT AVG(note) FROM ProductOpinionModel WHERE productModel.id = ?1")
    Double getMedia(Long productOp);


}
