/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.Rule;

/**
 *
 * @author minhp
 */
public interface RuleService {
    Rule getRule();
    boolean addOrUpdate(Rule rule);
}
