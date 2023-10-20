/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.repository.impl;

import com.dmp.pojo.Appointment;
import com.dmp.pojo.Medicine;
import com.dmp.pojo.Prescription;
import com.dmp.pojo.PrescriptionMedicine;
import com.dmp.pojo.PrescriptionMedicineDTO;
import com.dmp.pojo.User;
import com.dmp.repository.AppointmentRepository;
import com.dmp.repository.MedicineRepository;
import com.dmp.repository.PrescriptionMedicineRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author minhp
 */
@Repository
@Transactional
public class PrescriptionMedicineRepositoryImpl implements PrescriptionMedicineRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addOrUpdate(PrescriptionMedicineDTO prescriptionMedicineDTO) {
        Session session = this.factory.getObject().getCurrentSession();
        Prescription prescription = new Prescription();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = this.userRepository.getUserByUsername(authentication.getName());
            Appointment appointment = this.appointmentRepository.getAppointmentById(prescriptionMedicineDTO.getAppointmentId());
            appointment.setStatus("Pending payment");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            prescription.setCreatedDate(timestamp);
            prescription.setSymptom(prescriptionMedicineDTO.getSymptom());
            prescription.setDiagnose(prescriptionMedicineDTO.getDiagnose());
            prescription.setDescription(prescriptionMedicineDTO.getDescription());
            prescription.setAppointmentId(appointment);
            prescription.setDoctorId(user);
            session.save(prescription);
            
            List<Medicine> medicines = prescriptionMedicineDTO.getMedicines();
            for(Medicine m : medicines) {
                PrescriptionMedicine pm = new PrescriptionMedicine();
                pm.setMedicineId(m);
                pm.setPrescriptionId(prescription);
                pm.setQuantity(m.getQuantity());
                pm.setPrice(m.getPrice());
                Medicine medicine = this.medicineRepository.getMedicineById(m.getId());
                medicine.setQuantity(medicine.getQuantity() - m.getQuantity());
                
                session.save(pm);
            }
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<PrescriptionMedicine> q = b.createQuery(PrescriptionMedicine.class);
        Root<PrescriptionMedicine> root = q.from(PrescriptionMedicine.class);
        q.select(root);

        Predicate condition = b.equal(root.get("prescriptionId").get("id"), id);

        q.where(condition);
        
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<PrescriptionMedicine> getPrescriptions(Map<String, Date> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<PrescriptionMedicine> q = b.createQuery(PrescriptionMedicine.class);
        Root<PrescriptionMedicine> root = q.from(PrescriptionMedicine.class);
        
        q.select(root);
        
        if(params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
//            Predicate p1 = null;
//            Predicate p2 = null;
            
            Date fromDate = params.get("fromDate");
            if(fromDate != null) {
                predicates.add(b.greaterThanOrEqualTo(root.get("prescriptionId").get("createdDate"), fromDate));
//                p1 = b.greaterThanOrEqualTo(root.get("createdDate"), fromDate);
            }
            Date toDate = params.get("toDate");
            if(toDate != null) {
                predicates.add(b.lessThanOrEqualTo(root.get("prescriptionId").get("createdDate"), toDate));
//                p2 = b.lessThanOrEqualTo(root.get("createdDate"), toDate);
            }
            
            q.where(predicates.toArray(new Predicate[0]));

        }
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

}
