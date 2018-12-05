/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AppointmentDAO;
import dao.StudentDetailDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Appointment;
import model.AppointmentComparator;
import model.Student;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class StudentAppointmentsController implements java.io.Serializable {

    //Main Variables
    private Student student;
    private final AppointmentDAO DB;
    private final StudentDetailDAO STUDENT_DB;
    private final AppointmentComparator AC;
    private ArrayList<Appointment> scheduledAppointments;
    //Flags
    private String canceledName;
    private boolean cancellationSuccess, cancellationFailure;

    public StudentAppointmentsController() {
        DB = new AppointmentDAO();
        STUDENT_DB = new StudentDetailDAO();
        AC = new AppointmentComparator();
    }

    public final void viewAppointments(int id) {
        try {
            student = STUDENT_DB.getStudent(id);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Couldn't Load Student");
        }
        scheduledAppointments = DB.getScheduledAppointments(student);
        Collections.sort(scheduledAppointments, AC);
    }

    public void cancelAppointment(Appointment selected) {
        canceledName = selected.getUniversity().getName();
        DB.unscheduleAppointment(selected);
        cancellationSuccess = true;
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the cancellationSuccess
     */
    public boolean isCancellationSuccess() {
        return cancellationSuccess;
    }

    /**
     * @param cancellationSuccess the cancellationSuccess to set
     */
    public void setCancellationSuccess(boolean cancellationSuccess) {
        canceledName = "";
        this.cancellationSuccess = cancellationSuccess;
    }

    /**
     * @return the cancellationFailure
     */
    public boolean isCancellationFailure() {
        return cancellationFailure;
    }

    /**
     * @param cancellationFailure the cancellationFailure to set
     */
    public void setCancellationFailure(boolean cancellationFailure) {
        this.cancellationFailure = cancellationFailure;
    }

    /**
     * @return the canceledName
     */
    public String getCanceledName() {
        return canceledName;
    }

    /**
     * @param canceledName the canceledName to set
     */
    public void setCanceledName(String canceledName) {
        this.canceledName = canceledName;
    }

}
