/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.PaymentDTO;

/**
 *
 * @author minhp
 */
public interface PaymentRepository {
    boolean addOrUpdate(PaymentDTO paymentDTO);
}
