/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.Payment;
import com.dmp.pojo.PaymentDTO;
import com.dmp.repository.PaymentRepository;
import com.dmp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean addOrUpdate(PaymentDTO paymentDTO) {
        return this.paymentRepository.addOrUpdate(paymentDTO);
    }
    
}
