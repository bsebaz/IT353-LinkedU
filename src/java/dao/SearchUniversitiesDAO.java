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
import java.util.List;
import model.University;

/**
 *
 * @author Bailey
 */
public class SearchUniversitiesDAO implements DAOInterface, java.io.Serializable {
    public List<University> getUniversities(String searchTerm) throws SQLException{
            
        List<University> universities = new ArrayList<University>();
        
        try (Connection DBConn = connect()) {
            String insertString = "";
            
            PreparedStatement pstmt = null;
            
            if(searchTerm == null){
                insertString = "SELECT * FROM LinkedUDB.universities";
                
                pstmt = DBConn.prepareStatement( insertString );
            }
            else{
                searchTerm = searchTerm.trim();
                insertString = "SELECT * FROM LinkedUDB.universities WHERE UPPER(name) LIKE ?";

                // Note the use of a diff class, called PreparedStatement
                pstmt = DBConn.prepareStatement( insertString );
                pstmt.setString( 1, "%"+searchTerm.toUpperCase()+"%");
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                University university = new University(rs.getInt("universityId"),
                        rs.getInt("accountId"),
                        rs.getString("name"), 
                        rs.getString("city"), 
                        rs.getString("state"), 
                        rs.getString("studentPopulation"), 
                        rs.getString("cost"),
                        rs.getString("accentColor"),
                        rs.getBoolean("featured"),
                        rs.getString("applicationUrl")
                );
                universities.add(university);
            }
            
            DBConn.close();
        }
        
        return universities;
    }
    
    public void saveFeatured(List<University> universities){
        try (Connection db = connect()) {
                for (University university : universities) {
                    boolean featured = university.isFeatured();
                    int universityId = university.getUniversityId();

                    String query = "UPDATE LinkedUDB.universities SET featured = ? WHERE universityId = ?";
                    PreparedStatement pstmt = null;

                    pstmt = db.prepareStatement(query);
                    pstmt.setBoolean(1, featured);
                    pstmt.setInt(2, universityId);
                    int rowCount = pstmt.executeUpdate();
                }
            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void removeUniversity(int universityId){
        try (Connection db = connect()) {

            String query = "DELETE FROM LinkedUDB.universities WHERE universityId = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, universityId);
            int rowCount = pstmt.executeUpdate();

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
