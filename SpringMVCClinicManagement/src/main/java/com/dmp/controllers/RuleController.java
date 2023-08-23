/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Rule;
import com.dmp.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author minhp
 */
@Controller
public class RuleController {
    @Autowired
    private RuleService ruleService;
    
    @GetMapping("/rules")
    public String list(Model model) {
        Rule rule = this.ruleService.getRule();
        if(rule != null) {
            model.addAttribute("rule", rule);
        }
        return "rules";
    }
    
    @GetMapping("/rules/update/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        Rule rule = this.ruleService.getRule();
        if(rule != null) {
            model.addAttribute("rule", rule);
        }
        return "updateRule";
    }
    
    @PostMapping("/rules")
    public String add(@ModelAttribute(value = "rule") Rule r) {
        if (this.ruleService.addOrUpdate(r) == true) {
            return "redirect:/rules";
        }

        return "updateRule";
    }
}
