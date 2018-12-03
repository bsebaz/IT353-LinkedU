/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HomeDAO;
import java.sql.SQLException;
import model.University;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author slfx7
 */
@ManagedBean
@ViewScoped
public class HomeController implements java.io.Serializable {

    private ArrayList<University> featuredUniversities;
    private final HomeDAO DB;

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

    public String navToUniversity(int id) {
        return "universityDetail?faces-redirect=true&universityId="+id;
    }
}
