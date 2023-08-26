/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.pojo.PrescriptionMedicineDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface PrescriptionMedicineService {
    boolean addOrUpdate(PrescriptionMedicineDTO prescriptionMedicineDTO);
    List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(int id);
    List<PrescriptionMedicine> getPrescriptions(Map<String, Date> params);
}
