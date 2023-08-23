/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.UserShift;
import com.dmp.repository.UserShiftRepository;
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
public class UserShiftRepositoryImpl implements UserShiftRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public boolean addOrUpdate(UserShift userShift) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (userShift.getId() == null) {
                session.save(userShift);
            } else {
                session.update(userShift);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserShift> getUserShift(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<UserShift> q = b.createQuery(UserShift.class);
        Root root = q.from(UserShift.class);
        q.select(root);

        q.orderBy(b.desc(root.get("id")));

        Query query = session.createQuery(q);

//        if (params != null) {
//            String page = params.get("page");
//            if (page != null && !page.isEmpty()) {
//                int p = Integer.parseInt(page);
//                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
//
//                query.setMaxResults(pageSize);
//                query.setFirstResult((p - 1) * pageSize);
//            }
//        }
        return query.getResultList();
    }

    @Override
    public List<UserShift> getUserShiftByShiftId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<UserShift> query = b.createQuery(UserShift.class);
        Root<UserShift> root = query.from(UserShift.class);

        query.select(root).where(b.equal(root.get("shiftId"), id));

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public boolean deleteUserShiftByUserIdAndShiftId(int userId, int shiftId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaDelete<UserShift> query = b.createCriteriaDelete(UserShift.class);
        Root<UserShift> root = query.from(UserShift.class);

        query.where(
                b.and(
                        b.equal(root.get("userId"), userId),
                        b.equal(root.get("shiftId"), shiftId)
                )
        );

        Query q = session.createQuery(query);

        return q.executeUpdate() > 0;
    }

    @Override
    public List<UserShift> getUserShiftByUserId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<UserShift> query = b.createQuery(UserShift.class);
        Root<UserShift> root = query.from(UserShift.class);

        query.select(root).where(b.equal(root.get("userId"), id));

        Query q = session.createQuery(query);

        return q.getResultList();    }

}
