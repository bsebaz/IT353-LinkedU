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
import model.User;

/**
 *
 * @author slfx7
 */
public class AccountDAO implements DAOInterface, java.io.Serializable {

    public boolean login(User user) {
        boolean result = false;

        try (Connection db = connect()) {
            String username = user.getUsername();
            String password = user.getPassword();
            String sql = "SELECT * FROM LINKEDUDB.ACCOUNTS WHERE USERNAME = ?";
            PreparedStatement stmt = db.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //User was found, get password from DB
                String dbUsername = rs.getString("USERNAME");
                String dbPassword = rs.getString("PASSWORD");
                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    //Login was successful, set the user's attributes
                    int dbUserID = rs.getInt("ACCOUNTID");
                    String dbAccountType = rs.getString("ACCOUNTTYPE");
                    boolean dbAdmin = rs.getBoolean("ISADMIN");
                    user.setUserID(dbUserID);
                    user.setAccountType(dbAccountType);
                    user.setAdmin(dbAdmin);
                    //Set reutrn value to true
                    result = true;
                }
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    public static boolean insertAccount(User account)
    {
                String myDB = "jdbc:derby://localhost:1527/LinkedUDB";// connection string
                Connection DBConn = null;
                Statement stmt = null;
                
                String insertString = "INSERT INTO LINKEDUDB.Accounts(USERNAME, PASSWORD, ACCOUNTTYPE, ISADMIN) "
                + "VALUES('" + account.getUsername() + "','"
                                    + account.getPassword() + "','"
                                    + account.getAccountType() + "',"
                                    + "0" + "')";
                
                
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

