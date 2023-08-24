/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.controllers;

import com.dmp.pojo.Medicine;
import com.dmp.pojo.Unit;
import com.dmp.service.MedicineService;
import com.dmp.service.UnitService;
import com.dmp.validator.MedicineNameValidator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
public class MedicineController {

    @Autowired
    private MedicineService medicineService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private Environment env;
    @Autowired
    private MedicineNameValidator medicineNameValidator;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(medicineNameValidator);
    }

    @ModelAttribute
    public void commonAttr(Model model) {
        List<Unit> units = this.unitService.getUnits();
        if (units != null && !units.isEmpty()) {
            model.addAttribute("unit", units);
        }

    }

    @GetMapping("/medicines")
    public String medicine(Model model, @RequestParam Map<String, String> params) {
        List<Medicine> medicines = this.medicineService.getMedicines(params);
        if (medicines != null && !medicines.isEmpty()) {
            model.addAttribute("medicine", medicines);
        }
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.medicineService.countMedicine();

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "medicines";
    }

    @GetMapping("/medicines/add")
    public String list(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "addMedicine";
    }

    @PostMapping("/medicines")
    public String add(@ModelAttribute(value = "medicine") @Valid Medicine m, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.medicineService.addOrUpdate(m) == true) {
                return "redirect:/medicines";
            }
        }
        return "addMedicine";
    }

    @GetMapping("/medicines/update/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        Medicine medicine = this.medicineService.getMedicineById(id);
        if (medicine != null) {
            model.addAttribute("medicine", medicine);
        }
        return "addMedicine";
    }

    @DeleteMapping("/medicines/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.medicineService.deleteMedicineById(id);
    }

}
