/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Appointment;
import com.dmp.pojo.Payment;
import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.service.AppointmentService;
import com.dmp.service.PaymentService;
import com.dmp.service.PrescriptionMedicineService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private PrescriptionMedicineService prescriptionMedicineService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/statics")
    public String load(Model model, @RequestParam Map<String, String> params) throws ParseException {
        Map<String, Date> p = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        double totalFee = 0;
        double totalMedicine = 0;
        
        
        
        String fromDateStr = params.get("fromDate");
        if (fromDateStr != null && !fromDateStr.isEmpty()) {
            Date fromDate = dateFormat.parse(fromDateStr);
            p.put("fromDate", fromDate);
        }
        
        String toDateStr = params.get("toDate");
        if (toDateStr != null && !toDateStr.isEmpty()) {
            Date toDate = dateFormat.parse(toDateStr);
            p.put("toDate", toDate);
        }
//        String dateString = "2023-08-24";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dateFormat.parse(dateString);
//        params.put("fromDate", date);
        Long quantity = this.appointmentService.countAppointments(p);
        List<Appointment> appointments = this.appointmentService.getAppointments(p);
        List<PrescriptionMedicine> pms = this.prescriptionMedicineService.getPrescriptions(p);
        List<Payment> payments = this.paymentService.getPayments(p);
        
        for(Payment payment : payments) {
            totalFee += Double.parseDouble(payment.getFee().toString());
        }
        for(PrescriptionMedicine pm : pms) {
            totalMedicine += pm.getQuantity() * Double.parseDouble(pm.getPrice().toString());
        }
        if (quantity != null) {
            model.addAttribute("quantity", quantity);
            model.addAttribute("totalFee", totalFee);
            model.addAttribute("totalMedicine", totalMedicine);
        }
        return "statics";
    }
}
