/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.service;

import com.dmp.pojo.UserShift;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface UserShiftService {
    boolean addOrUpdate(UserShift userShift);
    List<UserShift> getUserShift(Map<String, String> params);
    List<UserShift> getUserShiftByShiftId(int id);
    boolean deleteUserShiftByUserIdAndShiftId(int userId, int shiftId);
    List<UserShift> getUserShiftByUserId(int id);
}
