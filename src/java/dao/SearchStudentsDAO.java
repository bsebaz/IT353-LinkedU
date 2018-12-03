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
import model.Student;

/**
 *
 * @author Bailey
 */
public class SearchStudentsDAO implements DAOInterface, java.io.Serializable {
    public List<Student> getStudents(String searchTerm) throws SQLException{
            
        List<Student> students = new ArrayList<Student>();
        
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
                Student student = new Student(rs.getInt("accountId"),
                        rs.getInt("studentId"), 
                        rs.getString("firstName"), 
                        rs.getString("lastName"), 
                        rs.getString("age"), 
                        rs.getString("school"), 
                        rs.getString("yearGraduated"), 
                        rs.getString("gpa"));
                
                students.add(student);
            }
            
            DBConn.close();
        }
        
        return students;
    }
}
