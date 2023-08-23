/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.pojo.PrescriptionMedicineDTO;
import com.dmp.repository.PrescriptionMedicineRepository;
import com.dmp.service.PrescriptionMedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class PrescriptionMedicineServiceImpl implements PrescriptionMedicineService{
    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    @Override
    public boolean addOrUpdate(PrescriptionMedicineDTO prescriptionMedicineDTO) {
        return this.prescriptionMedicineRepository.addOrUpdate(prescriptionMedicineDTO);
    }

    @Override
    public List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(int id) {
        return this.prescriptionMedicineRepository.getPrescriptionMedicineByPrescriptionId(id);
    }
    
}
