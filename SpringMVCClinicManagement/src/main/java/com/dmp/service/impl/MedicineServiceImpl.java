/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Medicine;
import com.dmp.pojo.User;
import com.dmp.repository.MedicineRepository;
import com.dmp.service.MedicineService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> getMedicines(Map<String, String> params) {
        return this.medicineRepository.getMedicines(params);
    }

    @Override
    public Long countMedicine() {
        return this.medicineRepository.countMedicine();
    }

    @Override
    public boolean addOrUpdate(Medicine medicine) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        medicine.setCreatedDate(timestamp);
        medicine.setUpdatedDate(timestamp);
        return this.medicineRepository.addOrUpdate(medicine);
    }

    @Override
    public Medicine getMedicineById(int id) {
        return this.medicineRepository.getMedicineById(id);
    }

    @Override
    public boolean deleteMedicineById(int id) {
        return this.medicineRepository.deleteMedicineById(id);
    }

}
