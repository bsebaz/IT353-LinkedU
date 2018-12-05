/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import model.Student;
import model.User;

/**
 *
 * @author kchris4
 */
public class StudentDAO implements DAOInterface, java.io.Serializable{
    
    public static boolean insertStudent(Student student)
    {
                String myDB = "jdbc:derby://localhost:1527/LinkedUDB";// connection string
                Connection DBConn = null;
                Statement stmt = null;
                
                String insertString = "INSERT INTO LINKEDUDB.Students(FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA) "
                + "VALUES('" + student.getFirstName() + "','"
                                    + student.getLastName() + "','"
                                    + student.getAge() + "',"
                                    + student.getSchool() + "',"
                                    + student.getGraduationYear() + "',"
                                    + student.getGpa() + "')";
                
                
                try {
                   DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
                   //stmt = DBConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                   System.out.println(insertString);
                   stmt = DBConn.createStatement();
                   stmt.executeUpdate(insertString);
                   
                }
                catch(Exception e){
                    
                    e.printStackTrace();
              
                    System.out.println("EXCEPTION: unable to insert user");
                    return false;
                }
                
            
            
        return true;
     }
    
}
