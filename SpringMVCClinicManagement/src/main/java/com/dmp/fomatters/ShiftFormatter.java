/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.fomatters;

import com.dmp.pojo.Shift;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author minhp
 */
public class ShiftFormatter implements Formatter<Shift> {

    @Override
    public String print(Shift shift, Locale locale) {
        return String.valueOf(shift.getId());
    }

    @Override
    public Shift parse(String shiftId, Locale locale) throws ParseException {
        int id = Integer.parseInt(shiftId);
        return new Shift(id);
    }
    
}
