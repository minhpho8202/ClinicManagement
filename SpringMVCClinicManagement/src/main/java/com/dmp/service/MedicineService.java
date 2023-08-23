/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.Medicine;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface MedicineService {
    List<Medicine> getMedicines(Map<String, String> params);
    Long countMedicine();
    boolean addOrUpdate(Medicine medicine);
    Medicine getMedicineById(int id);
    boolean deleteMedicineById(int id);
}
