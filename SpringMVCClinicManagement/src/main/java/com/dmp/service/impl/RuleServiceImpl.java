/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Rule;
import com.dmp.repository.RuleRepository;
import com.dmp.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class RuleServiceImpl implements RuleService{
    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public Rule getRule() {
        return this.ruleRepository.getRule();
    }

    @Override
    public boolean addOrUpdate(Rule rule) {
        return this.ruleRepository.addOrUpdate(rule);
    }
}
