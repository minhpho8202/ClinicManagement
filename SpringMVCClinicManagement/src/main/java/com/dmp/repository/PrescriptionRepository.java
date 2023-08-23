/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.Prescription;
import java.util.List;

/**
 *
 * @author minhp
 */
public interface PrescriptionRepository {
    boolean addOrUpdate(Prescription prescription);
    Prescription getPrescriptionByAppointmentId(int id);
    List<Prescription> getPrescriptionsByPatientId(int id);
}
