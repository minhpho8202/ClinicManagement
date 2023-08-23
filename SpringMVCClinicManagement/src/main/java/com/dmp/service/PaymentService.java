/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.Payment;
import com.dmp.pojo.PaymentDTO;

/**
 *
 * @author minhp
 */
public interface PaymentService {
    boolean addOrUpdate(PaymentDTO paymentDTO);
}
