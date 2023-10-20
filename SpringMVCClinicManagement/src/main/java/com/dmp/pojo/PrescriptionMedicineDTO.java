/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author minhp
 */
@Getter
@Setter
public class PrescriptionMedicineDTO {
    String symptom;
    String diagnose;
    int appointmentId;
    List<Medicine> medicines;
    private String description;

    public PrescriptionMedicineDTO() {
    }

    public PrescriptionMedicineDTO(String symptom, String diagnose, int appointmentId, List<Medicine> medicines, String desString) {
        this.symptom = symptom;
        this.diagnose = diagnose;
        this.appointmentId = appointmentId;
        this.medicines = medicines;
        this.description = desString;
    }
}
