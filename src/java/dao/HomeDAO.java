/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.University;

/**
 *
 * @author Bailey
 */
public class HomeDAO implements DAOInterface, java.io.Serializable {

    public ArrayList<University> getFeaturedUniversities() throws SQLException {
        ArrayList<University> featuredUniversities = new ArrayList();

        try (Connection db = connect()) {
            String query = "SELECT * FROM LinkedUDB.universities WHERE featured = 1";
            PreparedStatement pstmt = db.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                University featured = new University(
                        rs.getInt("universityId"),
                        rs.getInt("accountId"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("studentPopulation"),
                        rs.getString("cost"),
                        rs.getString("accentColor"),
                        rs.getBoolean("featured"),
                        rs.getString("applicationUrl"),
                        rs.getString("imagePath")
                );
                featuredUniversities.add(featured);
            }        
            rs.close();
            pstmt.close();
            db.close();
        }
        return featuredUniversities;
    }
}
