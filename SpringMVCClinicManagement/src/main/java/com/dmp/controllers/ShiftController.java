/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Shift;
import com.dmp.pojo.User;
import com.dmp.pojo.UserShift;
import com.dmp.service.ShiftService;
import com.dmp.service.UserService;
import com.dmp.service.UserShiftService;
import com.dmp.validator.WebAppValidator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author minhp
 */
@Controller
public class ShiftController {

    @Autowired
    private ShiftService shiftService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserShiftService userShiftService;
    @Autowired
    private WebAppValidator shiftValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(shiftValidator);
    }

    @GetMapping("/shifts")
    public String list(Model model, @RequestParam Map<String, String> params) {
        List<Shift> shifts = this.shiftService.getShifts(params);
        List<UserShift> userShift = this.userShiftService.getUserShift(params);
        if (shifts != null && !shifts.isEmpty()) {
            model.addAttribute("shift", shifts);
        }
        if (userShift != null && !userShift.isEmpty()) {
            model.addAttribute("userShift", userShift);
        }
        return "shifts";
    }

    @GetMapping("/shifts/add")
    public String addView(Model model) {
        model.addAttribute("shift", new Shift());
        return "addShift";
    }

    @PostMapping("/shifts")
    public String add(@ModelAttribute(value = "shift") @Valid Shift s, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.shiftService.addOrUpdate(s) == true) {
                return "redirect:/shifts";
            }
        }
        return "addShift";
    }

    @GetMapping("/shifts/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        Shift shift = this.shiftService.getShiftById(id);
        if (shift != null) {
            model.addAttribute("shift", shift);
        }

        return "addShift";
    }

    @DeleteMapping("/shifts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.shiftService.deleteShiftById(id);
    }

//    @GetMapping("/shifts/{id}/employee")
//    public String addEmployeeView(Model model, @PathVariable(value = "id") int id,
//            @RequestParam Map<String, String> params) {
//        Shift shift = this.shiftService.getShiftById(id);
//        params.put("shift", "add");
//        List<User> users = this.userService.getUsers(params);
//        List<UserShift> userShiftList = this.userShiftService.getUserShiftByShiftId(id);
//        if (shift != null) {
//            model.addAttribute("shift", shift);
//        }
//        if (users != null && !users.isEmpty()) {
//            model.addAttribute("user", users);
//        }
//        if (userShiftList != null && !userShiftList.isEmpty()) {
//            model.addAttribute("userShiftList", userShiftList);
//        }
//
//        model.addAttribute("userShift", new UserShift());
//
//        return "addEmployee";
//    }
}
