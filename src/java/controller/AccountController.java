/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class AccountController implements java.io.Serializable {

    /**
     * @return the badLogin
     */
    public boolean isBadLogin() {
        return badLogin;
    }

    /**
     * @param badLogin the badLogin to set
     */
    public void setBadLogin(boolean badLogin) {
        this.badLogin = badLogin;
    }

    private User user;
    private boolean loggedIn;
    private boolean accessDenied;
    private boolean badLogin;

    public AccountController() {
        user = new User();
        loggedIn = false;
        accessDenied = false;
        badLogin = false;
    }

    /**
     * Redirects the user to the login page if they try to access a login only page
     */
    public void checkIfNotLoggedIn() {
        if (!loggedIn) {
            accessDenied = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
        }
    }

    /**
     * Redirects the user to the home page if they try to access a "not logged in" page
     */
    public void checkIfAlreadyLoggedIn() {
        if (!loggedIn) {
            accessDenied = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("home?faces-redirect=true");
        }
    }

    public void checkIfAdmin() {
        if (!user.isAdmin()) {
            accessDenied = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("accessDenied");
        }
    }

    public String login() {
        
        boolean isGood;
        String returnString = "login?faces-redirect=true";
        try
        {
            isGood = verifyUser();
        }
        
        catch(SQLException e)
        {
            isGood = false;    
        }
        if(isGood == true)
        {
            loggedIn = true;
            returnString = "home?faces-redirect=true";
            return returnString;
        }
        //Remove later
//        user.setUsername("TestAccount");
//        user.setAdmin(true);
//        user.setAccountType("student");
//        user.setUserID(1);
        return returnString;
    }
    
    public boolean verifyUser() throws SQLException {
        AccountDAO anAccountDAO = new AccountDAO();    // Creating a new object each time.
        ResultSet result = anAccountDAO.findUser(user.getUsername()); // Doing anything with the object after this?
//        user = AccountDAO.findUser(user.getUserID());
        
        if (!result.first())
        {
            badLogin = true;
            return false; 
        }
        else
        {
            String tempPass = result.getString("PASSWORD");
            
            if(!user.getPassword().equals(tempPass))
            {
                badLogin = true;
                return false;
            }
            else
            {
                setLoggedIn(true);
                return true;
            }
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // the above is unnecessary once the session is invalidated
        return "home?faces-redirect=true";
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the accessDenied
     */
    public boolean isAccessDenied() {
        return accessDenied;
    }

    /**
     * @param accessDenied the accessDenied to set
     */
    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }
}
