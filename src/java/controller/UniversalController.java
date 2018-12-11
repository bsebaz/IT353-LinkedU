/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDAO;
import dao.UniversityDAO;
import java.text.NumberFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversalController implements java.io.Serializable {

    private final NumberFormat NF = NumberFormat.getInstance();
    private final StudentDAO STUDENT_DB = new StudentDAO();
    private final UniversityDAO UNIVERSITY_DB = new UniversityDAO();

    public String formatNumber(String number) {
        double num;
        try {
            num = Double.parseDouble(number);
        }
        catch(NumberFormatException e) {
            return number;
        }
        return NF.format(num);
    }
    
    public String getFormattedStudentName(int userID) {
        return STUDENT_DB.getStudentName(userID);
    }
    
    public String getUniversityName(int userID) {
        return UNIVERSITY_DB.getUniversityName(userID);
    }
}
