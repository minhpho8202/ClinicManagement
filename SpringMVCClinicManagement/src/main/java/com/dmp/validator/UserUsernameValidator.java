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
//@Component
public class UserUsernameValidator implements Validator{
    
    private final UserService userService;

    @Autowired
    public UserUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target;
        List<User> users = this.userService.getUsers(null);
        if (u.getId() != null) {
            for (int i = 0; i < users.size(); i++) {
                if(u.getId() == users.get(i).getId()) {
                    users.remove(i);
                    break;
                }
            }
        }
        if(users != null && !users.isEmpty()) {
            for(User user: users) {
                if(user.getUsername().equals(u.getUsername()))
                    errors.rejectValue("username", "user.username.duplicateErr");
                if(user.getEmail().equals(u.getEmail()))
                    errors.rejectValue("email", "user.email.duplicateErr");
            }
        }
    }
}
