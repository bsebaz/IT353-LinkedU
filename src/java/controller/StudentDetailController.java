/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDetailDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import javax.mail.Part;
import model.Student;
import model.StudentDetails;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class StudentDetailController implements DetailsInterface {

    private Student student;
    private final StudentDetailDAO DB = new StudentDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;
    private List<StudentDetails> studentDetails;
    private Part file;
    
    @PostConstruct
    public void viewDetails() {
        //Get studentId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("studentId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested user
        if (id != null) {
            try {
                student = DB.getStudent(Integer.parseInt(id));
                studentDetails = DB.getStudentDetails(student.getUserId());
            } catch (SQLException | NumberFormatException e) {
                System.out.println("Couldn't find requested user");
                e.getLocalizedMessage();
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
    
     public void upload() throws IOException, MessagingException{
        try (InputStream input = file.getInputStream()) {
            String fileName = file.getFileName();
            Files.copy(input, new File("images", fileName).toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
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

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }
}
