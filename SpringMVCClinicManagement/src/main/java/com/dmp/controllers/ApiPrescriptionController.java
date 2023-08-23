/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Prescription;
import com.dmp.service.PrescriptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiPrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

//    @GetMapping(path = "/prescriptions/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<Prescription> getPrescriptionByAppointmentId(@PathVariable("id") int id) {
//        Prescription prescription = this.prescriptionService.getPrescriptionByAppointmentId(id);
//
////        if (appointment == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//
//        return new ResponseEntity<>(prescription, HttpStatus.OK);
//    }
//    @GetMapping(path = "/prescriptions/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<Prescription> getPrescriptionByAppointmentId(@RequestParam(name = "appointmentId") int id) {
//        Prescription prescription = this.prescriptionService.getPrescriptionByAppointmentId(id);
//
////        if (appointment == null) {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//        return new ResponseEntity<>(prescription, HttpStatus.OK);
//    }

    @GetMapping(path = "/prescriptions/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> getPrescriptions(
            @RequestParam(name = "appointmentId", required = false) Integer appointmentId,
            @RequestParam(name = "patientId", required = false) Integer patientId
    ) {
        if (appointmentId != null) {
            Prescription prescription = this.prescriptionService.getPrescriptionByAppointmentId(appointmentId);
            if (prescription != null) {
                return new ResponseEntity<>(prescription, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else if (patientId != null) {
            List<Prescription> prescriptions = this.prescriptionService.getPrescriptionsByPatientId(patientId);
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid request. Please provide either appointmentId or patientId.", HttpStatus.BAD_REQUEST);
        }
    }

}
