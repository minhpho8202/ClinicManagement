/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Rule;
import com.dmp.repository.RuleRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author minhp
 */
@Repository
@Transactional
public class RuleRepositoryImpl implements RuleRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Rule getRule() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Rule> query = b.createQuery(Rule.class);
        Root<Rule> root = query.from(Rule.class);
        
        query.select(root).where(b.equal(root.get("id"), 1));

        Query q = session.createQuery(query);
        
        return (Rule) q.getSingleResult();
    }

    @Override
    public boolean addOrUpdate(Rule rule) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (rule.getId() == null) {
                session.save(rule);
            } else {
                session.update(rule);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
