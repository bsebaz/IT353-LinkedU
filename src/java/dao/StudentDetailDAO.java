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
import model.Student;

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
                student = new Student(rs.getInt("studentId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("age"), rs.getString("school"), rs.getString("yearGraduated"), rs.getString("gpa"));
            }
            rs.close();
            pstmt.close();
            db.close();
        }

        return student;
    }
}
