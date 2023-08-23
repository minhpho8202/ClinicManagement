/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.User;
import com.dmp.pojo.UserShift;
import com.dmp.service.UserService;
import com.dmp.service.UserShiftService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author minhp
 */
@RestController
@RequestMapping("/api")
public class ApiUserShiftController {
    @Autowired
    private UserShiftService userShiftService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/shifts/")
    @CrossOrigin
    public ResponseEntity<List<UserShift>> getAppointmentById(Principal principal) {
        User user = this.userService.getUserByUsername(principal.getName());
        List<UserShift> us = this.userShiftService.getUserShiftByUserId(user.getId());

//        if (appointment == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(us, HttpStatus.OK);
    }
}
