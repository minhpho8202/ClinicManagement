/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Appointment;
import com.dmp.repository.AppointmentRepository;
import com.dmp.service.AppointmentService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        appointment.setCreatedDate(timestamp);
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

    @Override
    public List<Appointment> getAppointments(Map<String, Date> params) {
        return this.appointmentRepository.getAppointments(params);
    }

    @Override
    public Long countAppointments(Map<String, Date> params) {
        return this.appointmentRepository.countAppointments(params);
    }
    
}
