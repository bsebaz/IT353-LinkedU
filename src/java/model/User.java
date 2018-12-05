/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AccountDAO;
import dao.StudentDAO;

/**
 *
 * @author slfx7
 */
public class User implements java.io.Serializable {

    private String username;
    private String password;
    private int userID;
    private String email;
    private boolean admin;
    private String accountType;

    public User() {
        admin = false;
        username = "";
        password = "";
        accountType = "";
        userID = -1;
        email = "";
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the AccountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the AccountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean attemptUserSignUp()
    {
       // boolean goodStudentInsert;
        boolean goodAccountInsert;

        goodAccountInsert = AccountDAO.insertAccount(this);

        return goodAccountInsert;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
