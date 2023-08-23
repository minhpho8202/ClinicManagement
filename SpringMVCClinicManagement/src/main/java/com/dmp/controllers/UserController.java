/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Role;
import com.dmp.pojo.User;
import com.dmp.service.RoleService;
import com.dmp.service.UserService;
import com.dmp.validator.UserUsernameValidator;
import com.dmp.validator.WebAppValidator;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author minhp
 */
@Controller
public class UserController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebAppValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @ModelAttribute
    public void commonAttr(Model model) {
        List<Role> roles = this.roleService.getRoles();
        if (roles != null && !roles.isEmpty()) {
            model.addAttribute("role", roles);
        }

    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @GetMapping("/users/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user";
    }

    @PostMapping("/users")
    public String add(@ModelAttribute(value = "user") @Valid User u, BindingResult rs, Model model) {

        if (!rs.hasErrors()) {
            if (this.userService.addOrUpdate(u) == true) {
                return "redirect:/";
            }
        }

        return "user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.userService.deleteUserById(id);
    }
}
