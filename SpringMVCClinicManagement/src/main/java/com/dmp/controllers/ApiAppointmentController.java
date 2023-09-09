/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Appointment;
import com.dmp.service.AppointmentService;
import com.dmp.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointments/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public boolean add(@RequestBody Appointment appointment) {
        if (appointment.getId() != null) {
            appointment.setStatus("Confirmed");
            return this.appointmentService.addOrUpdate(appointment);
        }
        appointment.setStatus("Pending confirmation");

        return this.appointmentService.addOrUpdate(appointment);
    }

    @GetMapping("/appointments/")
    @CrossOrigin
    public ResponseEntity<List<Appointment>> list(@RequestParam(name = "role") String role, @RequestParam(name = "id") int id) {
        String status = null;
        List<Appointment> appointments = null;

        switch (role) {
            case "ROLE_NURSE":
                status = "Pending confirmation";
                List<Appointment> pc = this.appointmentService.getAppointment(status);
                status = "Pending payment";
                List<Appointment> pp = this.appointmentService.getAppointment(status);
                appointments = new ArrayList<>(pc);
                appointments.addAll(pp);
                break;
            case "ROLE_DOCTOR":
                status = "Confirmed";
                appointments = this.appointmentService.getAppointment(status);
                break;
            case "ROLE_PATIENT":
                appointments = this.appointmentService.getAppointmentByPatientId(id);
                break;
        }

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping(path = "/appointments/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") int id) {
        Appointment appointment = this.appointmentService.getAppointmentById(id);

//        if (appointment == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable(value = "id") int id) {
        this.appointmentService.deleteAppointmentById(id);
    }

//    @GetMapping("/test/")
//    public ResponseEntity<List<Appointment>> test() {
//        return new ResponseEntity<>(this.appointmentService.getAppointmentByPatientId(28), HttpStatus.OK);
//    }
}
