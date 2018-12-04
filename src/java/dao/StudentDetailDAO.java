/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Student;
import model.StudentDetails;

/**
 *
 * @author slfx7
 */
public class StudentDetailDAO implements DAOInterface, java.io.Serializable {

    public Student getStudent(int id) throws SQLException {
        Student student = null;
        
        try (Connection db = connect()) {
            String query = "SELECT * FROM LinkedUDB.students WHERE studentID = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(rs.getInt("accountId"),
                        rs.getInt("studentId"), 
                        rs.getString("firstName"), 
                        rs.getString("lastName"), 
                        rs.getString("age"), 
                        rs.getString("school"), 
                        rs.getString("yearGraduated"), 
                        rs.getString("gpa"));
            }
            rs.close();
            pstmt.close();
            db.close();
        }

        return student;
    }
    
    public int updateStudent(Student student){
        
        int rowCount = 0;
        try (Connection db = connect()){
            
            int studentId = student.getStudentId();
            String firstName = student.getFirstName().replaceAll("\\s+","");
            String lastName = student.getLastName().replaceAll("\\s+","");
            String age = student.getAge().replaceAll("\\s+","");
            String school = student.getSchool().trim();
            String graduationYear = student.getGraduationYear().replaceAll("\\s+","");
            String gpa = student.getGpa().replaceAll("\\s+","");
            
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            
            boolean valid = true;
            
            if (firstName.length() < 2 || firstName.length() > 25){
                valid = false;
            }
            if (lastName.length() < 2 || lastName.length() > 25){
                valid = false;
            }
            if (age.length() < 2 || age.length() > 25){
                valid = false;
            }
            if (school.length() < 2 || school.length() > 25){
                valid = false;
            }
            if (graduationYear.length() < 2 || graduationYear.length() > 25){
                valid = false;
            }
            if (gpa.length() < 2 || gpa.length() > 25){
                valid = false;
            }
            
            if (valid){
                
                String query = "UPDATE LinkedUDB.students SET firstName = ?, lastName = ?, age = ?, school = ?, yearGraduated = ?, gpa = ? WHERE studentID = ?";
                PreparedStatement pstmt = null;

                pstmt = db.prepareStatement(query);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, age);
                pstmt.setString(4, school);
                pstmt.setString(5, graduationYear);
                pstmt.setString(6, gpa);
                pstmt.setInt(7, studentId);
                rowCount = pstmt.executeUpdate();
                
                if (rowCount == 1){
                    FacesContext.getCurrentInstance().addMessage("studentEdit:success", new FacesMessage("Profile updated successfully"));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage("studentEdit:error", new FacesMessage("Error updating profile"));
                }
                
                db.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }
    
    public int updateStudentDetails(List<StudentDetails> studentDetails){
        
        int rowCount = 0;
        try (Connection db = connect()){
            
            for (StudentDetails studentDetail: studentDetails){
                int detailId = studentDetail.getDetailId();
                String detailType = studentDetail.getDetailType();
                String detailName = studentDetail.getDetailName();
                String detailContent = studentDetail.getDetailContent();
                
                String query = "UPDATE LinkedUDB.studentDetails SET detailType = ?, detailName = ?, detailContent = ? WHERE detailID = ?";
                PreparedStatement pstmt = null;

                pstmt = db.prepareStatement(query);
                pstmt.setString(1, detailType);
                pstmt.setString(2, detailName);
                pstmt.setString(3, detailContent);
                pstmt.setInt(4, detailId);
                rowCount = pstmt.executeUpdate();
                
                if (rowCount == 1){
                    FacesContext.getCurrentInstance().addMessage("studentEdit:success", new FacesMessage("Profile updated successfully"));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage("studentEdit:error", new FacesMessage("Error updating profile"));
                    break;
                }
            }
                
            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }
    
    public int addNewDetail(Student student){
        int rowCount = 0;
        try (Connection db = connect()){
            
            int studentId = student.getStudentId();
                
            String query = "INSERT INTO LinkedUDB.studentDetails (studentId) VALUES (?)";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, studentId);
            rowCount = pstmt.executeUpdate();

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }
    
    public int removeDetail(int detailId){
        int rowCount = 0;
        try (Connection db = connect()){
                
            String query = "DELETE FROM LinkedUDB.studentDetails WHERE detailId = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, detailId);
            rowCount = pstmt.executeUpdate();

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return rowCount;
    }
    
    public List<StudentDetails> getStudentDetails(int studentId) throws SQLException{
            
        List<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
        
        try (Connection DBConn = connect()) {
            String insertString = "";
            
            PreparedStatement pstmt = null;
            
            insertString = "SELECT * FROM LinkedUDB.studentDetails WHERE studentId = ?";
                
            pstmt = DBConn.prepareStatement( insertString );
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                StudentDetails studentDetail = new StudentDetails(rs.getInt("detailId"),
                        rs.getInt("studentId"), 
                        rs.getString("detailType"), 
                        rs.getString("detailName"), 
                        rs.getString("detailContent"));
                
                studentDetails.add(studentDetail);
            }
            
            DBConn.close();
        }
        
        return studentDetails;
    }
}
