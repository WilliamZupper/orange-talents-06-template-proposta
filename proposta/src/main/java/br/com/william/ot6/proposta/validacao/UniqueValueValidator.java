package br.com.william.ot6.proposta.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private EntityManager em;

    private Class<?> klazz;
    private String campo;

    public UniqueValueValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result= this.em.createQuery("select 1 from "+this.klazz.getName()+" where "+ campo+"=:campo")
                .setParameter("campo",value)
                .getResultList()
                .isEmpty();

        if(this.campo.equals("documento") && !result){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe proposta para esse usuario");
        }

        return result;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.klazz = constraintAnnotation.Classe();
        this.campo = constraintAnnotation.campo();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}