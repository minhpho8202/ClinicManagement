/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Appointment;
import com.dmp.repository.AppointmentRepository;
import com.dmp.service.AppointmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public boolean addOrUpdate(Appointment appointment) {
        return this.appointmentRepository.addOrUpdate(appointment);
    }

    @Override
    public List<Appointment> getAppointment(String status) {
        return this.appointmentRepository.getAppointment(status);
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(int id) {
        return this.appointmentRepository.getAppointmentByPatientId(id);
    }

    @Override
    public boolean deleteAppointmentById(int id) {
        return this.appointmentRepository.deleteAppointmentById(id);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return this.appointmentRepository.getAppointmentById(id);
    }
    
}
