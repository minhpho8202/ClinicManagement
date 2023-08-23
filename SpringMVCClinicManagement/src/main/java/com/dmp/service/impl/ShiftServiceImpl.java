/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Shift;
import com.dmp.repository.ShiftRepository;
import com.dmp.service.ShiftService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public List<Shift> getShifts(Map<String, String> params) {
        return this.shiftRepository.getShifts(params);
    }

    @Override
    public Long countShift() {
        return this.shiftRepository.countShift();
    }

    @Override
    public boolean addOrUpdate(Shift shift) {
        return this.shiftRepository.addOrUpdate(shift);
    }

    @Override
    public Shift getShiftById(int id) {
        return this.shiftRepository.getShiftById(id);
    }

    @Override
    public boolean deleteShiftById(int id) {
        return this.shiftRepository.deleteShiftById(id);
    }
    
}
