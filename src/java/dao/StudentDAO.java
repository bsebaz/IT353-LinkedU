/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Student;

/**
 *
 * @author kchris4
 */
public class StudentDAO implements DAOInterface, java.io.Serializable {

    public boolean insertStudent(Student student, int accountId) {
        ArrayList vars = new ArrayList(Arrays.asList(accountId, student.getFirstName(), student.getLastName(), student.getAge(), student.getSchool(), student.getGraduationYear(), student.getGpa()));
        String query = "INSERT INTO LINKEDUDB.Students(ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        return updateDB(query, vars);
    }

    public String getStudentName(int userID) {
        String query = "SELECT FIRSTNAME, LASTNAME FROM LINKEDUDB.STUDENTS WHERE ACCOUNTID = ?";
        String name = "Profile";
        try (Connection db = connect()) {
            PreparedStatement pstmt = null;
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("firstName") + " " + rs.getString("lastName");
            }
            rs.close();
            pstmt.close();
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to get student's name");
            System.out.println(e.getLocalizedMessage());
        }
        return name;
    }

    public int getStudentID(int userID) {
        String query = "SELECT STUDENTID FROM LINKEDUDB.STUDENTS WHERE ACCOUNTID = ?";
        int id = -1;
        try (Connection db = connect()) {
            PreparedStatement pstmt = null;
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("STUDENTID");
            }
            rs.close();
            pstmt.close();
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to get student id");
            System.out.println(e.getLocalizedMessage());
        }
        return id;
    }
}
