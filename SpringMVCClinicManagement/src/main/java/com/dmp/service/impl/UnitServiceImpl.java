/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Unit;
import com.dmp.repository.UnitRepository;
import com.dmp.service.UnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class UnitServiceImpl implements UnitService{
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getUnits() {
        return this.unitRepository.getUnits();
    }
    
}
