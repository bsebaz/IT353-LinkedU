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

/**
 *
 * @author Bailey
 */
public class SearchUniversitiesDAO implements DAOInterface {
    public List<List<String>> getUniversities(String searchTerm) throws SQLException{
            
        List<List<String>> students = new ArrayList<List<String>>();
        
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
                ArrayList<String> singleStudent = new ArrayList<String>();
                singleStudent.add(rs.getString("name"));
                singleStudent.add(rs.getString("city"));
                singleStudent.add(rs.getString("state"));
                singleStudent.add(rs.getString("studentPopulation"));
                singleStudent.add(rs.getString("cost"));
                
                students.add(singleStudent);
            }
            
            DBConn.close();
        }
        
        return students;
    }
}
