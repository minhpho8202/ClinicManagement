/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.service.impl;

import com.dmp.pojo.UserShift;
import com.dmp.repository.UserShiftRepository;
import com.dmp.service.UserShiftService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author minhp
 */
@Service
public class UserShiftServiceImpl implements UserShiftService{
    @Autowired
    private UserShiftRepository userShiftRepository;

    @Override
    public boolean addOrUpdate(UserShift userShift) {
        return this.userShiftRepository.addOrUpdate(userShift);
    }

    @Override
    public List<UserShift> getUserShift(Map<String, String> params) {
        return this.userShiftRepository.getUserShift(params);
    }

    @Override
    public List<UserShift> getUserShiftByShiftId(int id) {
        return this.userShiftRepository.getUserShiftByShiftId(id);
    }

    @Override
    public boolean deleteUserShiftByUserIdAndShiftId(int userId, int shiftId) {
        return this.userShiftRepository.deleteUserShiftByUserIdAndShiftId(userId, shiftId);
    }

    @Override
    public List<UserShift> getUserShiftByUserId(int id) {
        return this.userShiftRepository.getUserShiftByUserId(id);
    }
    
}
