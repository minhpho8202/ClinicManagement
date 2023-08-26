/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.Payment;
import com.dmp.pojo.PaymentDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface PaymentService {
    boolean addOrUpdate(PaymentDTO paymentDTO);
    List<Payment> getPayments(Map<String, Date> params);
}
