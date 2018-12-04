/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversityDetailDAO;
import java.sql.SQLException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.University;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversityDetailController implements DetailsInterface {

    private University university;
    private final UniversityDetailDAO DB = new UniversityDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    @PostConstruct
    public void viewDetails() {
        //Get universityId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("universityId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested university
        if (id != null) {
            try {
                university = DB.getUniversity(Integer.parseInt(id));
            } catch (SQLException | NumberFormatException e) {
                System.out.println("Couldn't find requested university");
                e.getLocalizedMessage();
            }
        } //Otherwise, return the current user's page
        else if (userID > 0) {
            try {
                university = DB.getUniversity(userID);
            } catch (SQLException e) {
                System.out.println("Couldn't find requested university");
            }
        }
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }
}
