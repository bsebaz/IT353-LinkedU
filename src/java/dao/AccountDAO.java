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
import java.util.ArrayList;
import java.util.Arrays;
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
                    String dbEmail = rs.getString("EMAIL");
                    String dbAccountType = rs.getString("ACCOUNTTYPE");
                    boolean dbAdmin = rs.getBoolean("ISADMIN");
                    user.setUserID(dbUserID);
                    user.setEmail(dbEmail);
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

    public int insertAccount(User account, String accountType) {
        int accountId = -1;
        ArrayList vars = new ArrayList(Arrays.asList(account.getUsername(), account.getPassword(), account.getEmail(), accountType, false));
        String insertString = "INSERT INTO LINKEDUDB.Accounts(USERNAME, PASSWORD, EMAIL, ACCOUNTTYPE, ISADMIN) "
                + "VALUES(?, ?, ?, ?, ?)";
        try (Connection db = connect()) {
            PreparedStatement stmt = db.prepareStatement(insertString, new String[]{"ACCOUNTID"});
            setVars(stmt, vars);
            stmt.executeUpdate();

            ResultSet result = stmt.getGeneratedKeys();

            if (result.next()) {
                accountId = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: unable to insert user");
            return -1;
        }
        return accountId;
    }

    public boolean checkIfUserExists(User user) {
        String myDB = "jdbc:derby://localhost:1527/LinkedUDB";// connection string
        Connection DBConn = null;

        try {
            String givenUsername = user.getUsername();

            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String sql = "SELECT * FROM LINKEDUDB.ACCOUNTS WHERE USERNAME = ?";
            PreparedStatement pstmt = DBConn.prepareStatement(sql);
            pstmt.setString(1, givenUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //User already exists in DB
                return true;
            }
            rs.close();
            pstmt.close();
            //db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void removeAccount(int accountId) {
        ArrayList vars = new ArrayList(Arrays.asList(accountId));
        String query = "DELETE FROM LinkedUDB.accounts WHERE accountId = ?";
        updateDB(query, vars);
    }
}
