/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.pojo.PrescriptionMedicineDTO;
import com.dmp.service.PrescriptionMedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiPrescriptionMedicineController {
    @Autowired
    private PrescriptionMedicineService prescriptionMedicineService;
    
    @PostMapping("/rescriptions-medicines/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody PrescriptionMedicineDTO prescriptionMedicineDTO) {
        if (prescriptionMedicineDTO != null) {
            this.prescriptionMedicineService.addOrUpdate(prescriptionMedicineDTO);
        }
    }
    
    @GetMapping("/rescriptions-medicines/{id}/")
    @CrossOrigin
    public ResponseEntity<List<PrescriptionMedicine>> getPrescriptionMedicineByPrescriptionId(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.prescriptionMedicineService.getPrescriptionMedicineByPrescriptionId(id), HttpStatus.OK);
    }
}
