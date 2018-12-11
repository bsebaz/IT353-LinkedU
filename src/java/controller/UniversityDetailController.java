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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.University;
import model.UniversityDetails;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversityDetailController implements DetailsInterface, java.io.Serializable {

    private University university;
    private final UniversityDetailDAO DB = new UniversityDetailDAO();
    private List<UniversityDetails> universityDetails;
    private int userID;

    public void viewDetails(int userID) {
        //Set userID
        this.setUserID(userID);

        //Get universityId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("universityId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested university
        if (id != null) {
            try {
                university = DB.getUniversity(Integer.parseInt(id));
                if (university != null) {
                    universityDetails = DB.getUniversityDetails(university.getUniversityId());
                }
            } catch (SQLException | NumberFormatException e) {
                System.out.println("Couldn't find requested university");
                e.getLocalizedMessage();
            }
        } //Otherwise, return the current user's page
        else if (userID > 0) {
            try {
                university = DB.getUniversityByUser(userID);
                if (university != null) {
                    universityDetails = DB.getUniversityDetails(university.getUniversityId());
                }
            } catch (SQLException e) {
                System.out.println("Couldn't find requested university");
            }
        }
        if(university == null && universityDetails != null) {
            universityDetails.clear();
        }
    }

    public String updateUniversity() throws SQLException {
        DB.updateUniversity(university);
        DB.updateUniversityDetails(universityDetails);
        university = DB.getUniversity(university.getUniversityId());
        universityDetails = DB.getUniversityDetails(university.getUniversityId());
        return "universityEdit?universityId=" + university.getUniversityId();
    }

    public String addNewDetail() throws SQLException {
        DB.updateUniversityDetails(universityDetails);
        DB.addNewDetail(university); 
        universityDetails = DB.getUniversityDetails(university.getUniversityId());
        return "universityEdit?redirect=true&universityId=" + university.getUniversityId();
    }

    public String removeDetail(int detailId) throws SQLException {
        DB.updateUniversityDetails(universityDetails);
        DB.removeDetail(detailId);
        universityDetails = DB.getUniversityDetails(university.getUniversityId());
        return "universityEdit?redirect=true&universityId=" + university.getUniversityId();
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

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
