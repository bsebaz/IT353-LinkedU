/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HomeDAO;
import java.sql.SQLException;
import java.text.NumberFormat;
import model.University;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class HomeController {

    private ArrayList<University> featuredUniversities;
    private final HomeDAO DB;
    private final NumberFormat NF = NumberFormat.getInstance();
    
    public HomeController() {
        DB = new HomeDAO();
        try {
            featuredUniversities = DB.getFeaturedUniversities();
        } catch (SQLException e) {
            System.out.println("Could not get featured Universities");
            featuredUniversities = new ArrayList();
        }
    }

    /**
     * @return the featuredUniversities
     */
    public ArrayList<University> getFeaturedUniversities() {
        return featuredUniversities;
    }

    /**
     * @param featuredUniversities the featuredUniversities to set
     */
    public void setFeaturedUniversities(ArrayList<University> featuredUniversities) {
        this.featuredUniversities = featuredUniversities;
    }

    public String formatNumber(String number) {
        return NF.format(Double.parseDouble(number));
    }
    
    public String navToUniversity(int id) {
        return "profile?faces-redirect=true";
    }
}
