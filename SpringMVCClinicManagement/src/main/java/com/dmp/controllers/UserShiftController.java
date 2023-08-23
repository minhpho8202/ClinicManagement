/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.UserShift;
import com.dmp.service.UserShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author minhp
 */
@Controller
public class UserShiftController {

    @Autowired
    private UserShiftService userShiftService;

    @PostMapping("/user-shift")
    public String add(@ModelAttribute(value = "userShift") UserShift us, RedirectAttributes redirectAttributes) {
        if (this.userShiftService.addOrUpdate(us)) {
            redirectAttributes.addAttribute("shiftId", us.getShiftId().getId());
            return "redirect:/shifts/{shiftId}/employee";
        }
        return "redirect:/shifts/{shiftId}/employee";
    }
    
    @DeleteMapping("/shifts/{shiftId}/employee/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "shiftId") int shiftId,
            @PathVariable(value = "userId") int userId) {
        this.userShiftService.deleteUserShiftByUserIdAndShiftId(userId, shiftId);
    }
}
