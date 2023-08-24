/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.Appointment;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface AppointmentService {
    boolean addOrUpdate(Appointment appointment);
    List<Appointment> getAppointment(String status);
    List<Appointment> getAppointmentByPatientId(int id);
    boolean deleteAppointmentById(int id);
    Appointment getAppointmentById(int id);
    Long countAppointments(Map<String, Date> params);
//    public List<Appointment> getAppointments(Map<String, Date> params);
}
