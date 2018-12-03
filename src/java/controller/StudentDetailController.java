/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDetailDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import model.Student;
import model.StudentDetails;

/**
 *
 * @author slfx7
 */
@ManagedBean
@ViewScoped
public class StudentDetailController implements DetailsInterface {

    private Student student;
    private final StudentDetailDAO DB = new StudentDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;
    private List<StudentDetails> studentDetails;
    

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
                studentDetails = DB.getStudentDetails(student.getStudentId());
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
    
    public String updateStudent(){
        DB.updateStudent(student);
        DB.updateStudentDetails(studentDetails);
        return "studentEdit?studentId="+student.getStudentId();
    }
    
    public String addNewDetail() throws SQLException{
        DB.addNewDetail(student);
        return "studentEdit?redirect=true&studentId="+student.getStudentId();
    }
    public String removeDetail(int detailId) throws SQLException{
        DB.removeDetail(detailId);
        return "studentEdit?redirect=true&studentId="+student.getStudentId();
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

    /**
     * @return the studentDetails
     */
    public List<StudentDetails> getStudentDetails() {
        return studentDetails;
    }

    /**
     * @param studentDetails the studentDetails to set
     */
    public void setStudentDetails(List<StudentDetails> studentDetails) {
        this.studentDetails = studentDetails;
    }
}
