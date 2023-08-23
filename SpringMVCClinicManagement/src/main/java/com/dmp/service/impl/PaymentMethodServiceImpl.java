/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.PaymentMethod;
import com.dmp.repository.PaymentMethodRepository;
import com.dmp.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod getPaymentMethodByName(String name) {
        return this.paymentMethodRepository.getPaymentMethodByName(name);
    }
    
}
