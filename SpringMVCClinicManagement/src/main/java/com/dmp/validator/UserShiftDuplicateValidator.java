/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.validator;

import com.dmp.pojo.UserShift;
import com.dmp.service.UserShiftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author minhp
 */
public class UserShiftDuplicateValidator implements Validator{
    private final UserShiftService userShiftService;
    
    @Autowired
    public UserShiftDuplicateValidator(UserShiftService userShiftService) {
        this.userShiftService = userShiftService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserShift.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserShift us = (UserShift) target;
        List<UserShift> userShifts= this.userShiftService.getUserShiftByShiftId(us.getShiftId().getId());
        if(userShifts != null && !userShifts.isEmpty()) {
            for(UserShift userShift: userShifts) {
                if(userShift.getUserId().getId() == us.getUserId().getId()) {
                    errors.rejectValue("userId", "user.email.duplicateErr");
                    break;
                }
            }
        }
    }
    
}
