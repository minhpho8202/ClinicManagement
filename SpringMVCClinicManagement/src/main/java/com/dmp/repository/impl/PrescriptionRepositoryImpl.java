/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Prescription;
import com.dmp.repository.PrescriptionRepository;
import java.util.List;
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
public class PrescriptionRepositoryImpl implements PrescriptionRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public boolean addOrUpdate(Prescription prescription) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (prescription.getId() == null) {
                session.save(prescription);
            } else {
                session.update(prescription);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Prescription getPrescriptionByAppointmentId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> q = b.createQuery(Prescription.class);
        Root<Prescription> root = q.from(Prescription.class);
        
        Predicate condition = b.equal(root.get("appointmentId").get("id"), id);

        q.where(condition);
        
        Query query = session.createQuery(q);
        
        return (Prescription) query.getSingleResult();
    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> q = b.createQuery(Prescription.class);
        Root<Prescription> root = q.from(Prescription.class);
        q.select(root);

        Predicate condition = b.equal(root.get("appointmentId").get("patientId"), id);

        q.where(condition);
        
        Query query = session.createQuery(q);

        return query.getResultList();
    }
}
