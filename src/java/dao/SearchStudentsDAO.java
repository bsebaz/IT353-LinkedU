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
public class SearchStudentsDAO implements DAOInterface {
    public List<List<String>> getStudents(String searchTerm) throws SQLException{
            
        List<List<String>> students = new ArrayList<List<String>>();
        
        try (Connection DBConn = connect()) {
            String insertString = "";
            
            PreparedStatement pstmt = null;
            
            if(searchTerm == null){
                insertString = "SELECT * FROM LinkedUDB.students";
                
                pstmt = DBConn.prepareStatement( insertString );
            }
            else{
                
                if (searchTerm.contains(" ")){
                    searchTerm = searchTerm.trim();
                    String[] name = searchTerm.split(" ");
                    insertString = "SELECT * FROM LinkedUDB.students WHERE UPPER(firstName) LIKE ? AND UPPER(lastName) LIKE ?";

                    // Note the use of a diff class, called PreparedStatement
                    pstmt = DBConn.prepareStatement( insertString );
                    pstmt.setString( 1, "%"+name[0].toUpperCase()+"%");
                    pstmt.setString( 2, "%"+name[1].toUpperCase()+"%");
                }
                else {
                    searchTerm = searchTerm.trim();
                    insertString = "SELECT * FROM LinkedUDB.students WHERE UPPER(firstName) LIKE ? OR UPPER(lastName) LIKE ?";

                    // Note the use of a diff class, called PreparedStatement
                    pstmt = DBConn.prepareStatement( insertString );
                    pstmt.setString( 1, "%"+searchTerm.toUpperCase()+"%");
                    pstmt.setString( 2, "%"+searchTerm.toUpperCase()+"%");
                }
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                ArrayList<String> singleStudent = new ArrayList<String>();
                singleStudent.add(rs.getString("firstName"));
                singleStudent.add(rs.getString("lastName"));
                singleStudent.add(rs.getString("age"));
                singleStudent.add(rs.getString("school"));
                singleStudent.add(rs.getString("yearGraduated"));
                singleStudent.add(rs.getString("gpa"));
                
                students.add(singleStudent);
            }
            
            DBConn.close();
        }
        
        return students;
    }
}
