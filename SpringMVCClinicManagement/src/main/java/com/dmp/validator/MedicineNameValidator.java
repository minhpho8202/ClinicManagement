/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.validator;

import com.dmp.pojo.Medicine;
import com.dmp.service.MedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author minhp
 */
//@Component
public class MedicineNameValidator implements Validator{
    
    private final MedicineService medicineService;
    
    @Autowired
    public MedicineNameValidator(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Medicine.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Medicine m = (Medicine) target;
        List<Medicine> medicines = this.medicineService.getMedicines(null);
        if(m.getId() != null) {
            for(int i = 0; i < medicines.size(); i++) {
                if(m.getId() == medicines.get(i).getId()) {
                    medicines.remove(i);
                    break;
                }
            }
        }
        if(medicines != null && !medicines.isEmpty()) {
            for(Medicine medicine: medicines) {
                if(medicine.getName().equals(m.getName()))
                    errors.rejectValue("name", "medicine.name.duplicateErr");
            }
        }
    }
    
}
