/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.StudentDAO;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.University;
import model.Student;
import model.User;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class AccountController implements java.io.Serializable {

    AccountDAO db;
    private User user;
    private University signUpUniversity;
    private Student student;
    private boolean loggedIn;
    private boolean accessDenied;
    private boolean badLogin;
    private boolean badAccountInsert;

    public AccountController() {
        user = new User();
        student = new Student();
        loggedIn = false;
        accessDenied = false;
        badLogin = false;
        badAccountInsert = false;
        db = new AccountDAO();
        signUpUniversity = new University();
    }

    /**
     * Redirects the user to the login page if they try to access a login only
     * page
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
     * Redirects the user to the home page if they try to access a "not logged
     * in" page
     */
    public void checkIfAlreadyLoggedIn() {
        if (!loggedIn) {
            accessDenied = true;
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("home?faces-redirect=true");
        }
    }

    public void checkIfRecruiter() {
        if (!user.getAccountType().equals("recruiter") && !user.isAdmin()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("accessDenied?faces-redirect=true");
        }
    }

    public String login() {
        loggedIn = db.login(user);
        if (!loggedIn) {
            badLogin = true;
            return "login?faces-redirect=true";
        }
        return "home?faces-redirect=true";
//      Testing
//      user.setUsername("TestAccount");
//      user.setAdmin(true);
//      user.setAccountType("student");
//      user.setUserID(1);
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // the above is unnecessary once the session is invalidated
        return "home?faces-redirect=true";
    }

    public String attemptUserSignUp() {
        badAccountInsert = false;
        int accountId = -1;

        if (!AccountDAO.checkIfUserExists(this.user)) {
            accountId = AccountDAO.insertAccount(this.user);
        }

        if (accountId == -1) //username exists already
        {
            return "createAccount?faces-redirect=true";
        }

        badAccountInsert = StudentDAO.insertStudent(this.student, accountId);

        if (badAccountInsert == true && accountId != -1) {
            return "home?faces-redirect=true";
        } else {
            return "createAccount?faces-redirect=true";
        }
    }

    public String createUniversityAccount() {
        return "";
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    /**
     * @return the signUpUniversity
     */
    public University getSignUpUniversity() {
        return signUpUniversity;
    }

    /**
     * @param signUpUniversity the signUpUniversity to set
     */
    public void setSignUpUniversity(University signUpUniversity) {
        this.signUpUniversity = signUpUniversity;
    }

    /**
     * @return the goodAccountInsert
     */
    public boolean isGoodAccountInsert() {
        return badAccountInsert;
    }

    /**
     * @param goodAccountInsert the goodAccountInsert to set
     */
    public void setGoodAccountInsert(boolean goodAccountInsert) {
        this.badAccountInsert = goodAccountInsert;
    }
}
