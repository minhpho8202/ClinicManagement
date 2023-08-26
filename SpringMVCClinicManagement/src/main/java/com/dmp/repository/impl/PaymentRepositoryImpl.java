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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

    @Override
    public List<Payment> getPayments(Map<String, Date> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Payment> q = b.createQuery(Payment.class);
        Root<Payment> root = q.from(Payment.class);
        
        q.select(root);
        
        if(params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
//            Predicate p1 = null;
//            Predicate p2 = null;
            
            Date fromDate = params.get("fromDate");
            if(fromDate != null) {
                predicates.add(b.greaterThanOrEqualTo(root.get("createdDate"), fromDate));
//                p1 = b.greaterThanOrEqualTo(root.get("createdDate"), fromDate);
            }
            Date toDate = params.get("toDate");
            if(toDate != null) {
                predicates.add(b.lessThanOrEqualTo(root.get("createdDate"), toDate));
//                p2 = b.lessThanOrEqualTo(root.get("createdDate"), toDate);
            }
            
            q.where(predicates.toArray(new Predicate[0]));

        }
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

}
