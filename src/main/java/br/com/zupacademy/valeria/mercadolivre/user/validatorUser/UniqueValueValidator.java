package br.com.zupacademy.valeria.mercadolivre.user.validatorUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator  implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager em;

    private String fieldName;
    private Class<?> domainClass;


    @Override
    public void initialize(UniqueValue params) {
        fieldName = params.fieldName();
        domainClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = em.createQuery("SELECT 1 FROM" + domainClass.getName() + "WHERE" + fieldName + " = :value");
        query.setParameter("value", value);
        List<?> resultList = query.getResultList();
        
        return resultList.isEmpty();
    }
}
