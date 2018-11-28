/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class AccountController {

    private User user;
    private boolean loggedIn;
    private boolean accessDenied;

    public AccountController() {
        user = new User();
        loggedIn = false;
        accessDenied = false;
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
        //TO-DO provide authentication logic
        loggedIn = true;
        //Remove later
        user.setUsername("TestAccount");
        user.setAdmin(true);
        user.setAccountType("student");
        user.setUserID(1);
        return "home?faces-redirect=true";
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
