/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AppointmentDAO;
import dao.UniversityDetailDAO;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Appointment;
import model.University;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversityAppointmentsController implements java.io.Serializable {

    private University university;
    private AppointmentDAO db;
    private Date date;
    private Date start;
    private Date end;
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    public UniversityAppointmentsController() {
        loadUniversity();
        db = new AppointmentDAO();
        date = new Date();
        start = new Date();
        end = new Date();
    }

    @PostConstruct
    public final void loadUniversity() {
        UniversityDetailDAO universityDB = new UniversityDetailDAO();
        try {
            university = universityDB.getUniversity(userID);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Couldn't Load University");
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }

    public void createNewAppointment() {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Time sqlStart = new java.sql.Time(start.getTime());
        java.sql.Time sqlEnd = new java.sql.Time(start.getTime());
        db.createAppointment(sqlStart, sqlEnd, sqlDate, university);
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return db.getScheduledAppointments(university);
    }

    public ArrayList<Appointment> getUnscheduledAppointments() {
        return db.getUnscheduledAppointments(university);
    }

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
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

}
