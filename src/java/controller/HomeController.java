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
    private University selectedUniversity1;
    private University selectedUniversity2;
    private final HomeDAO DB;
    
    public HomeController() {
        DB = new HomeDAO();
        try {
            featuredUniversities = DB.getFeaturedUniversities();
        } catch (SQLException e) {
            System.out.println("Could not get featured Universities");
            featuredUniversities = new ArrayList();
        }
        //Testing
        selectedUniversity1 = featuredUniversities.get(0);
        selectedUniversity2 = featuredUniversities.get(1);
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
 
    public String navToUniversity(int id) {
        return "profile?faces-redirect=true";
    }

    /**
     * @return the selectedUniversity1
     */
    public University getSelectedUniversity1() {
        return selectedUniversity1;
    }

    /**
     * @param selectedUniversity1 the selectedUniversity1 to set
     */
    public void setSelectedUniversity1(University selectedUniversity1) {
        this.selectedUniversity1 = selectedUniversity1;
    }

    /**
     * @return the selectedUniversity2
     */
    public University getSelectedUniversity2() {
        return selectedUniversity2;
    }

    /**
     * @param selectedUniversity2 the selectedUniversity2 to set
     */
    public void setSelectedUniversity2(University selectedUniversity2) {
        this.selectedUniversity2 = selectedUniversity2;
    }
}
