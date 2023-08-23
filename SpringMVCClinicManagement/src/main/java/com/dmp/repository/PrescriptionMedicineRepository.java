/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.pojo.PrescriptionMedicineDTO;
import java.util.List;

/**
 *
 * @author minhp
 */
public interface PrescriptionMedicineRepository {
    boolean addOrUpdate(PrescriptionMedicineDTO prescriptionMedicineDTO);
    List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(int id);
}
