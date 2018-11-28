/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDetailDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import model.Student;

/**
 *
 * @author slfx7
 */
@ManagedBean
@RequestScoped
public class StudentDetailController implements DetailsInterface {

    private Student student;
    private final StudentDetailDAO DB = new StudentDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    public StudentDetailController() {
        retrieveStudent();
    }

    @PostConstruct
    private void retrieveStudent() {
        //Get studentId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("studentId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested user
        if (id != null) {
            try {
                student = DB.getStudent(Integer.parseInt(id));
            } catch (SQLException e) {
                System.out.println("Couldn't find requested user");
            } catch (NumberFormatException e) {
                System.out.println("Couldn't find requested user");
            }
        } //Otherwise, return the current user's page
        else {
            try {
                student = DB.getStudent(userID);
            } catch (SQLException e) {
                System.out.println("Couldn't find requested user");
            }
        }
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
