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
import java.sql.Statement;
import model.Student;

/**
 *
 * @author kchris4
 */
public class UniversityDAO implements DAOInterface, java.io.Serializable {

    public String getUniversityName(int userID) {
        String query = "SELECT NAME FROM LINKEDUDB.UNIVERSITIES WHERE ACCOUNTID = ?";
        String name = "Profile";
        try (Connection db = connect()) {
            PreparedStatement pstmt = null;
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("NAME");
            }
            rs.close();
            pstmt.close();
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to get university's name");
            System.out.println(e.getLocalizedMessage());
        }
        return name;
    }
}
