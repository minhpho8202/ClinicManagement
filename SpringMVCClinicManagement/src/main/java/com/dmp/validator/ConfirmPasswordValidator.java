/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.validator;

import com.dmp.pojo.User;
import com.dmp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author minhp
 */
@Component
public class ConfirmPasswordValidator implements Validator{

    private final UserService userService;

    @Autowired
    public ConfirmPasswordValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target;
        if(!u.getConfirmPassword().equals(u.getPassword())) {
            errors.rejectValue("confirmPassword", "user.confirm_password.matchErr");
        }
    }
    
}
