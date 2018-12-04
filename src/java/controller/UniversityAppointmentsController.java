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
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Appointment;
import model.AppointmentComparator;
import model.University;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversityAppointmentsController implements java.io.Serializable {

    //Main Variables
    private final AppointmentDAO DB;
    private final UniversityDetailDAO UNIVERSITY_DB;
    private final AppointmentComparator AC;
    private ArrayList<Appointment> scheduledAppointments;
    private ArrayList<Appointment> unscheduledAppointments;
    private University university;
    private Date date, start, end;
    //Editor Variables
    private Appointment selected;
    //Alerts
    private String canceledName;
    private boolean creationSuccess, editSuccess, deleteSuccess, cancellationSuccess;
    private boolean creationFailure, editFailure, deleteFailure, cancellationFailure;
    //Injected Values
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    public UniversityAppointmentsController() {
        DB = new AppointmentDAO();
        UNIVERSITY_DB = new UniversityDetailDAO();
        AC = new AppointmentComparator();
        date = new Date();
        start = new Date();
        end = new Date();
    }

    @PostConstruct
    public final void viewAppointments() {
        try {
            university = UNIVERSITY_DB.getUniversity(userID);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Couldn't Load University");
        }
        scheduledAppointments = DB.getScheduledAppointments(university);
        unscheduledAppointments = DB.getUnscheduledAppointments(university);
        Collections.sort(scheduledAppointments, AC);
        Collections.sort(unscheduledAppointments, AC);
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void onTimeSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void createNewAppointment() {
        DB.createAppointment(start, end, date, university);
        creationSuccess = true;
    }

    public String editAppointment(Appointment selected) {
        this.selected = selected;
        return "editAppointment?faces-redirect=true";
    }

    public String submitEdit() {
        DB.editAppointment(selected, selected.getStart(), selected.getEnd(), selected.getDate());
        editSuccess = true;
        return "appointments?faces-redirect=true";
    }

    public String cancelEdit() {
        return "appointments?faces-redirect=true";
    }

    public String deleteAppointment() {
        DB.deleteAppointment(selected);
        deleteSuccess = true;
        return "appointments?faces-redirect=true";
    }

    public void deleteAppointment(Appointment appointment) {
        DB.deleteAppointment(appointment);
        deleteSuccess = true;
    }

    public void cancelAppointment(Appointment appointment) {
        canceledName = appointment.getStudent().getFirstName() + " " + appointment.getStudent().getLastName();
        DB.unscheduleAppointment(appointment);
        cancellationSuccess = true;
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public ArrayList<Appointment> getUnscheduledAppointments() {
        return unscheduledAppointments;
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

    /**
     * @return the creationSuccess
     */
    public boolean isCreationSuccess() {
        return creationSuccess;
    }

    /**
     * @param creationSuccess the creationSuccess to set
     */
    public void setCreationSuccess(boolean creationSuccess) {
        this.creationSuccess = creationSuccess;
    }

    /**
     * @return the editSuccess
     */
    public boolean isEditSuccess() {
        return editSuccess;
    }

    /**
     * @param editSuccess the editSuccess to set
     */
    public void setEditSuccess(boolean editSuccess) {
        this.editSuccess = editSuccess;
    }

    /**
     * @return the creationFailure
     */
    public boolean isCreationFailure() {
        return creationFailure;
    }

    /**
     * @param creationFailure the creationFailure to set
     */
    public void setCreationFailure(boolean creationFailure) {
        this.creationFailure = creationFailure;
    }

    /**
     * @return the editFailure
     */
    public boolean isEditFailure() {
        return editFailure;
    }

    /**
     * @param editFailure the editFailure to set
     */
    public void setEditFailure(boolean editFailure) {
        this.editFailure = editFailure;
    }

    /**
     * @return the selected
     */
    public Appointment getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Appointment selected) {
        this.selected = selected;
    }

    /**
     * @param scheduledAppointments the scheduledAppointments to set
     */
    public void setScheduledAppointments(ArrayList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    /**
     * @param unscheduledAppointments the unscheduledAppointments to set
     */
    public void setUnscheduledAppointments(ArrayList<Appointment> unscheduledAppointments) {
        this.unscheduledAppointments = unscheduledAppointments;
    }

    /**
     * @return the deleteSuccess
     */
    public boolean isDeleteSuccess() {
        return deleteSuccess;
    }

    /**
     * @param deleteSuccess the deleteSuccess to set
     */
    public void setDeleteSuccess(boolean deleteSuccess) {
        this.deleteSuccess = deleteSuccess;
    }

    /**
     * @return the deleteFailure
     */
    public boolean isDeleteFailure() {
        return deleteFailure;
    }

    /**
     * @param deleteFailure the deleteFailure to set
     */
    public void setDeleteFailure(boolean deleteFailure) {
        this.deleteFailure = deleteFailure;
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
     * @return the cancelledName
     */
    public String getCanceledName() {
        return canceledName;
    }

    /**
     * @param cancelledName the cancelledName to set
     */
    public void setCanceledName(String cancelledName) {
        this.canceledName = cancelledName;
    }
}
