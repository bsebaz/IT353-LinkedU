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
import model.Student;
import model.University;

/**
 *
 * @author slfx7
 */
public class UniversityDetailDAO implements DAOInterface, java.io.Serializable {

    public University getUniversity(int id) throws SQLException {
        University university = null;
        
        try (Connection db = connect()) {
            String query = "SELECT * FROM LinkedUDB.universities WHERE universityID = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                university = new University(
                        rs.getInt("universityId"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("studentPopulation"),
                        rs.getString("cost"),
                        rs.getBoolean("featured")
                );
            }
            rs.close();
            pstmt.close();
            db.close();
        }

        return university;
    }
}
