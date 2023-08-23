/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author minhp
 */
@Getter
@Setter
public class PaymentDTO {
    private BigDecimal moneyReceived;
    private String paymentMethod;
    private int patientId;
    private int appoimentId;
    private BigDecimal fee;

    public PaymentDTO() {
    }

    public PaymentDTO(BigDecimal moneyReceived, String paymentMethod, int patientId, int appoimentId, BigDecimal fee) {
        this.moneyReceived = moneyReceived;
        this.paymentMethod = paymentMethod;
        this.patientId = patientId;
        this.appoimentId = appoimentId;
        this.fee = fee;
    }
    
}
