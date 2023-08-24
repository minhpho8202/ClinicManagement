/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Appointment;
import com.dmp.service.AppointmentService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author minhp
 */
@Controller
public class StaticsController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/statics")
    public String load(Model model, @RequestParam Map<String, Date> params) throws ParseException {
//        String dateString = "2023-08-24";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dateFormat.parse(dateString);
//        params.put("fromDate", date);
        Long quantity = this.appointmentService.countAppointments(params);
        if (quantity != null) {
            model.addAttribute("quantity", quantity);
        }
        return "statics";
    }
}
