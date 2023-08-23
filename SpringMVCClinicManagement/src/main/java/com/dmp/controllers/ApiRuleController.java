/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Rule;
import com.dmp.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiRuleController {
    @Autowired
    private RuleService ruleService;
    
    @GetMapping("/rules/")
    @CrossOrigin
    public ResponseEntity<Rule> details() {
        Rule rule = this.ruleService.getRule();
        return new ResponseEntity<>(rule, HttpStatus.OK);
    }
}
