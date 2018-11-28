/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author slfx7
 */
public class User implements java.io.Serializable {
    private String username;
    private String password;
    private boolean admin;
    private String accountType;

    public User() {
        admin = false;
        username = "";
        password = "";
        accountType = "";
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
     * @param AccountType the AccountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
