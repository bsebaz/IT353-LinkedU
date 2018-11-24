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
    User user;
    boolean loggedIn;
    boolean accessDenied;

    public AccountController() {
        loggedIn = false;
        accessDenied = false;
    }

    public void checkIfLoggedIn() {
        if (!loggedIn) {
            accessDenied = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login");
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
        return "home?faces-redirect=true";
    }

    public void logout() {

    }
}
