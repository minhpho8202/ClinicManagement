/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.fomatters;

import com.dmp.pojo.Unit;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author minhp
 */
public class UnitFormatter implements Formatter<Unit>{

    @Override
    public String print(Unit unit, Locale locale) {
        return String.valueOf(unit.getId());
    }

    @Override
    public Unit parse(String unitId, Locale locale) throws ParseException {
        int id = Integer.parseInt(unitId);
        return new Unit(id);
    }
    
}
