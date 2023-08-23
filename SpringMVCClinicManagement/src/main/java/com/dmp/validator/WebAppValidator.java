/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.validator;

import com.dmp.pojo.User;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author minhp
 */
@Component
public class WebAppValidator implements Validator{
    @Autowired
    @Lazy
    @Qualifier("validator")
    private javax.validation.Validator beanValidator;
    
    private Set<Validator> springValidators;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> beans = this.beanValidator.validate(target);
        for(ConstraintViolation obj: beans) {
            errors.rejectValue(obj.getPropertyPath().toString(), obj.getMessageTemplate(), obj.getMessage());
        }
        for(Validator v: this.springValidators)
            v.validate(target, errors);
    }

    /**
     * @param springValidators the springValidators to set
     */
    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
    
}
