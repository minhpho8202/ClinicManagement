/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Role;
import com.dmp.repository.RoleRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getRoles() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Role> q = b.createQuery(Role.class);
        Root<Role> root = q.from(Role.class);
        q.select(root);

        Predicate condition = b.notEqual(root.get("name"), "ROLE_ADMIN");
        q.where(condition);
        
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = b.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        
        query.select(root).where(b.equal(root.get("name"), name));

        Query q = session.createQuery(query);
        
        return (Role) q.getSingleResult();
    }

}
