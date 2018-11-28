/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
        //Temp data for testing
        featuredUniversities.add(new University(1, "Illinois State University", "Normal", "IL", "18000", "25000"));
        featuredUniversities.add(new University(2, "University of Illinois at Urbana–Champaign", "Champaign-Urbana", "IL", "34000", "31000"));
        featuredUniversities.add(new University(3, "Northern Illinois University", "DeKalb", "IL", "20000", "18000"));
        return featuredUniversities;

// Use this later when implementation is ready
//        try (Connection DBConn = connect()) {
//            String query = "";
//            PreparedStatement pstmt = null;
//
//            searchTerm = searchTerm.trim();
//            String[] name = searchTerm.split(" ");
//            query = "SELECT * FROM LinkedUDB.universities WHERE featured = 1";
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                University featured = new University(rs.getInt("universityId"), rs.getString("name"), rs.getString("city"), rs.getString("state"), rs.getString("studentPopulation"), rs.getString("cost"));
//                featuredUniversities.add(featured);
//            }
//            rs.close();
//            pstmt.close();
//            DBConn.close();
//        }
//        return featuredUniversities;
    }
}
