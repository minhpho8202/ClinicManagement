/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Shift;
import com.dmp.repository.ShiftRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
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
public class ShiftRepositoryImpl implements ShiftRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Shift> getShifts(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Shift> q = b.createQuery(Shift.class);
        Root root = q.from(Shift.class);
        q.select(root);

        q.orderBy(b.desc(root.get("id")));

        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public Long countShift() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<Shift> root = query.from(Shift.class);
        query.select(builder.count(root));

        Query q = session.createQuery(query);

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdate(Shift shift) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (shift.getId() == null) {
                session.save(shift);
            } else {
                session.update(shift);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Shift getShiftById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Shift> query = b.createQuery(Shift.class);
        Root<Shift> root = query.from(Shift.class);
        
        query.select(root).where(b.equal(root.get("id"), id));

        Query q = session.createQuery(query);
        
        return (Shift) q.getSingleResult();
    }

    @Override
    public boolean deleteShiftById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaDelete<Shift> query = b.createCriteriaDelete(Shift.class);
        Root<Shift> root = query.from(Shift.class);
        
        query.where(b.equal(root.get("id"), id));
        
        Query q = session.createQuery(query);
        
        return q.executeUpdate() > 0;
    }
    
}
