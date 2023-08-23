/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Prescription;
import com.dmp.repository.PrescriptionRepository;
import com.dmp.service.PrescriptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class PrescriptionServiceImpl implements PrescriptionService{
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public boolean addOrUpdate(Prescription prescription) {
        return this.prescriptionRepository.addOrUpdate(prescription);
    }

    @Override
    public Prescription getPrescriptionByAppointmentId(int id) {
        return this.prescriptionRepository.getPrescriptionByAppointmentId(id);
    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(int id) {
        return this.prescriptionRepository.getPrescriptionsByPatientId(id);
    }
}
