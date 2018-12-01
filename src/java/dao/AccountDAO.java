/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author slfx7
 */
public class AccountDAO implements DAOInterface, java.io.Serializable {
    
    public ResultSet findUser(String username)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        ResultSet foundUser = null;
        try {
            String myDB = "jdbc:derby://localhost:1527/LinkedUDB";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String selectString;
            Statement stmt = DBConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            selectString = "SELECT * FROM LinkedUDB.Accounts WHERE USERNAME LIKE '" + username + "'";

            foundUser = stmt.executeQuery(selectString);
//            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return foundUser;
    }
    
}
