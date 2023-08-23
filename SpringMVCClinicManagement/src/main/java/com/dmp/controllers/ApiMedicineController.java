/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Medicine;
import com.dmp.service.MedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiMedicineController {
    @Autowired
    private MedicineService medicineService;
    
    @DeleteMapping("/medicines/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.medicineService.deleteMedicineById(id);
    }
    
    @GetMapping("/medicines/")
    @CrossOrigin
    public ResponseEntity<List<Medicine>> list() {
        List<Medicine> medicines = this.medicineService.getMedicines(null);
        
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }
}
