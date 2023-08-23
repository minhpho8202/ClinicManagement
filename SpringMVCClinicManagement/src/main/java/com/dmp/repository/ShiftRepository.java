/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.Shift;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface ShiftRepository {
    List<Shift> getShifts(Map<String, String> params);
    Long countShift();
    boolean addOrUpdate(Shift shift);
    Shift getShiftById(int id);
    boolean deleteShiftById(int id);
}
