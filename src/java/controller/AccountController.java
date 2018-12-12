/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.StudentDAO;
import dao.UniversityDAO;
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

    private final AccountDAO DB;
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
        DB = new AccountDAO();
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

    public String checkUsername() {
        AccountDAO accountDAO = new AccountDAO();    // Creating a new object each time.
        boolean status = accountDAO.checkIfUserExists(user); // Doing anything with the object after this?
        if (!status) {
            return "";
        } else {
            return "User ID already in use.";
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

    public void checkIfAccountOwner(int id) {
        if (user.getUserID() != id && !user.isAdmin()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("accessDenied?faces-redirect=true");
        }
    }

    public String login() {
        loggedIn = DB.login(user);
        if (!loggedIn) {
            badLogin = true;
            return "login?faces-redirect=true";
        }
        return "home?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // the above is unnecessary once the session is invalidated
        return "home?faces-redirect=true";
    }

    public String createStudentAccount() {
        AccountDAO accountDB = new AccountDAO();
        boolean success;
        int accountId = -1;

        if (!accountDB.checkIfUserExists(user)) {
            accountId = accountDB.insertAccount(user, "student");
        }

        if (accountId == -1) //username exists already
        {
            badAccountInsert = true;
            return "createAccount?faces-redirect=true";
        }

        success = StudentDAO.insertStudent(student, accountId);

        if (success == true && accountId != -1) {
            return login();
        } else {
            badAccountInsert = true;
            DB.removeAccount(accountId);
            return "createAccount?faces-redirect=true";
        }
    }

    public String createUniversityAccount() {
        AccountDAO accountDB = new AccountDAO();
        UniversityDAO universityDB = new UniversityDAO();
        badAccountInsert = false;
        int accountId = -1;
        int universityID;

        if (!accountDB.checkIfUserExists(user)) {
            accountId = accountDB.insertAccount(user, "recruiter");
        }

        //If account insert failed
        if (accountId == -1) {
            badAccountInsert = true;
            return "createUniversityAccount?faces-redirect=true";
        }

        universityID = universityDB.insertUniversity(signUpUniversity, accountId);

        //If university insert failed
        if (universityID == -1) {
            badAccountInsert = true;
            DB.removeAccount(accountId);
            return "createUniversityAccount?faces-redirect=true";
        }

        if (badAccountInsert == true && accountId != -1) {
            return "createUniversityAccount?faces-redirect=true";
        } else {
            return "universityDetail.xhtml?faces-redirect=true&universityId=" + universityID;
        }
    }

    public int getStudentID() {
        StudentDAO studentDB = new StudentDAO();
        return studentDB.getStudentID(user.getUserID());
    }

    public int getUniversityID() {
        UniversityDAO universityDB = new UniversityDAO();
        return universityDB.getUniversityID(user.getUserID());
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
    public boolean isBadAccountInsert() {
        return badAccountInsert;
    }

    /**
     * @param goodAccountInsert the goodAccountInsert to set
     */
    public void setBadAccountInsert(boolean goodAccountInsert) {
        this.badAccountInsert = goodAccountInsert;
    }
}
