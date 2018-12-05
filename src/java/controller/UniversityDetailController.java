/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversityDetailDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.University;
import model.UniversityDetails;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class UniversityDetailController implements DetailsInterface {

    private University university;
    private final UniversityDetailDAO DB = new UniversityDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;
    private List<UniversityDetails> universityDetails;

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
                universityDetails = DB.getUniversityDetails(university.getUniversityId());
            } catch (SQLException e) {
                System.out.println("Couldn't find requested university");
            } catch (NumberFormatException e) {
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

    public String updateUniversity(){
        DB.updateUniversity(university);
        DB.updateUniversityDetails(universityDetails);
        return "universityEdit?universityId="+university.getUniversityId();
    }

    public String addNewDetail() throws SQLException{
        DB.addNewDetail(university);
        return "universityEdit?redirect=true&universityId="+university.getUniversityId();
    }
    public String removeDetail(int detailId) throws SQLException{
        DB.removeDetail(detailId);
        return "universityEdit?redirect=true&universityId="+university.getUniversityId();
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

    /**
     * @return the universityDetails
     */
    public List<UniversityDetails> getUniversityDetails() {
        return universityDetails;
    }

    /**
     * @param universityDetails the universityDetails to set
     */
    public void setUniversityDetails(List<UniversityDetails> universityDetails) {
        this.universityDetails = universityDetails;
    }
}
