/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.User;
import com.dmp.service.UserService;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author minhp
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        if(params.isEmpty()) {
            params.put("page", "1");
        }
        List<User> users = this.userService.getUsers(params);
        if(users != null && !users.isEmpty())
            model.addAttribute("user", users);
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.userService.countUser();

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "index";
    }
    

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam String lang, HttpServletRequest request) {
        if (lang.equals("en")) {
            LocaleResolver localeResolver = new SessionLocaleResolver();
            localeResolver.setLocale(request, null, Locale.US);
        } else if (lang.equals("vi")) {
            LocaleResolver localeResolver = new SessionLocaleResolver();
            localeResolver.setLocale(request, null, new Locale("vi"));
        }
        return "redirect:/";
    }
}
