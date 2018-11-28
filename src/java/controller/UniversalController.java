/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.NumberFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversalController {

    private final NumberFormat NF = NumberFormat.getInstance();

    public String formatNumber(String number) {
        return NF.format(Double.parseDouble(number));
    }
}
