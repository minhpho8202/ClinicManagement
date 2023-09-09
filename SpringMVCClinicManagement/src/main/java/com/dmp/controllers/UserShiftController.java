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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author minhp
 */
@Controller
public class UserShiftController {

    @Autowired
    private UserShiftService userShiftService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebAppValidator userShiftValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userShiftValidator);
    }

    @GetMapping("/shifts/{id}/employee")
    public String addEmployeeView(Model model, @PathVariable(value = "id") int id,
            @RequestParam Map<String, String> params) {
        Shift shift = this.shiftService.getShiftById(id);
        params.put("shift", "add");
        List<User> users = this.userService.getUsers(params);
        List<UserShift> userShiftList = this.userShiftService.getUserShiftByShiftId(id);
        if (shift != null) {
            model.addAttribute("shift", shift);
        }
        if (users != null && !users.isEmpty()) {
            model.addAttribute("user", users);
        }
        if (userShiftList != null && !userShiftList.isEmpty()) {
            model.addAttribute("userShiftList", userShiftList);
        }

        model.addAttribute("userShift", new UserShift());

        return "addEmployee";
    }

    @PostMapping("/user-shift")
    public String add(@ModelAttribute(value = "userShift") @Valid UserShift us, BindingResult rs, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("shiftId", us.getShiftId().getId());
        if (!rs.hasErrors()) {
            if (this.userShiftService.addOrUpdate(us)) {
                redirectAttributes.addAttribute("shiftId", us.getShiftId().getId());
                return "redirect:/shifts/{shiftId}/employee";
            }
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
