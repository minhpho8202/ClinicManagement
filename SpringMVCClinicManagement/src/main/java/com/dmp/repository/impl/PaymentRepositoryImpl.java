/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Appointment;
import com.dmp.pojo.Payment;
import com.dmp.pojo.PaymentDTO;
import com.dmp.pojo.PaymentMethod;
import com.dmp.pojo.User;
import com.dmp.repository.AppointmentRepository;
import com.dmp.repository.PaymentMethodRepository;
import com.dmp.repository.PaymentRepository;
import com.dmp.repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author minhp
 */
@Repository
@Transactional
public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public boolean addOrUpdate(PaymentDTO paymentDTO) {
        Session session = this.factory.getObject().getCurrentSession();
        Payment payment = new Payment();

        try {
            User patient = this.userRepository.getUserById(paymentDTO.getPatientId());
            PaymentMethod pm = this.paymentMethodRepository.getPaymentMethodByName(paymentDTO.getPaymentMethod());
            Appointment appointment = this.appointmentRepository.getAppointmentById(paymentDTO.getAppoimentId());
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            payment.setAmountReceived(paymentDTO.getMoneyReceived());
            payment.setCreatedDate(timestamp);
            payment.setPaymentMethodId(pm);
            payment.setAppointmentId(appointment);
            payment.setPatientId(patient);
            payment.setFee(paymentDTO.getFee());

            session.save(payment);
            
            appointment.setStatus("Completed");

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
