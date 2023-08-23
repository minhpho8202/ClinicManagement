/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dmp.repository;

import com.dmp.pojo.UserShift;
import java.util.List;
import java.util.Map;

/**
 *
 * @author minhp
 */
public interface UserShiftRepository {
    List<UserShift> getUserShift(Map<String, String> params);
    List<UserShift> getUserShiftByShiftId(int id);
    List<UserShift> getUserShiftByUserId(int id);
    boolean addOrUpdate(UserShift userShift);
    boolean deleteUserShiftByUserIdAndShiftId(int userId, int shiftId);
}
