/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Appointment;
import com.dmp.repository.AppointmentRepository;
import com.dmp.repository.RuleRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public boolean addOrUpdate(Appointment appointment) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            long count = this.countAppointmentsByDate(appointment.getCreatedDate());
            
            if(count >= this.ruleRepository.getRule().getAppointmentLimit()) {
                return false;
            }
            
            if (appointment.getId() == null) {
                session.save(appointment);
            } else {
                session.update(appointment);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAppointment(String status) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Appointment> q = b.createQuery(Appointment.class);
        Root<Appointment> root = q.from(Appointment.class);
        q.select(root);

        Predicate condition = b.equal(root.get("status"), status);
        q.where(condition);

        q.orderBy(b.asc(root.get("id")));

        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Appointment> q = b.createQuery(Appointment.class);
        Root<Appointment> root = q.from(Appointment.class);
        q.select(root);

        Predicate condition = b.equal(root.get("patientId").get("id"), id);

        q.where(condition);

        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean deleteAppointmentById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaDelete<Appointment> query = b.createCriteriaDelete(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);

        query.where(b.equal(root.get("id"), id));

        Query q = session.createQuery(query);

        return q.executeUpdate() > 0;
    }

    @Override
    public Appointment getAppointmentById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = b.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);

        query.select(root).where(b.equal(root.get("id"), id));

        Query q = session.createQuery(query);

        return (Appointment) q.getSingleResult();
    }

    @Override
    public long countAppointmentsByDate(Date date) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<Appointment> root = query.from(Appointment.class);
        query.select(builder.count(root));
        query.where(builder.equal(root.get("createdDate"), date));

        Query q = session.createQuery(query);

        return Long.parseLong(q.getSingleResult().toString());
    }

}
