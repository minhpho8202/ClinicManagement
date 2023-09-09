/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.validator;

import com.dmp.pojo.Shift;
import com.dmp.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author minhp
 */
public class ShiftTimeValidator implements Validator {

    private final ShiftService shiftService;

    @Autowired
    public ShiftTimeValidator(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Shift.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Shift s = (Shift) target;
        if (s.getStartTime() != null && s.getEndTime() != null) {
            if (s.getStartTime().after(s.getEndTime()) || s.getStartTime().equals(s.getEndTime())) {
                errors.rejectValue("startTime", "shift.time.invalid");
            }
        }
    }

}
