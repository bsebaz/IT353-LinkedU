/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AppointmentDAO;
import dao.StudentDetailDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Appointment;
import model.AppointmentComparator;
import model.University;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class ScheduleAppointmentsController implements java.io.Serializable, DetailsInterface {

    //Main Variables
    private final AppointmentDAO DB;
    private final AppointmentComparator AC;
    private University university;
    private ArrayList<Appointment> unscheduledAppointments;
    //Flags
    private boolean success;
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    public ScheduleAppointmentsController() {
        DB = new AppointmentDAO();
        AC = new AppointmentComparator();
    }

    public String viewAppointments(University university) {
        this.university = university;
        unscheduledAppointments = DB.getUnscheduledAppointments(university);
        Collections.sort(unscheduledAppointments, AC);
        return "scheduleAppointment?faces-redirect=true";
    }

    public ArrayList<Appointment> getUnscheduledAppointments() {
        return unscheduledAppointments;
    }

    public void schedule(Appointment appointment) {
        StudentDetailDAO studentDB = new StudentDetailDAO();
        try {
            DB.scheduleAppointment(appointment, studentDB.getStudent(userID));
        } catch (SQLException e) {
            System.out.println("Couldn't schedule appointment");
            e.getLocalizedMessage();
            success = false;
        }
        success = true;
        unscheduledAppointments = DB.getUnscheduledAppointments(university);
        Collections.sort(unscheduledAppointments, AC);
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

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
