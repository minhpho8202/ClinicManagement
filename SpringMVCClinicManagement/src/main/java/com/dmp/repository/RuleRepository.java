/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.Rule;

/**
 *
 * @author minhp
 */
public interface RuleRepository {
    boolean addOrUpdate(Rule rule);
    Rule getRule();
}
