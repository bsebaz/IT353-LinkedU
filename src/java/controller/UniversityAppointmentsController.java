/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AppointmentDAO;
import java.util.ArrayList;
import model.Appointment;
import model.University;

/**
 *
 * @author slfx7
 */
public class UniversityAppointmentsController {
    private University university;
    private ArrayList<Appointment> appointments;
    AppointmentDAO db;

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
     * @return the appointments
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * @param appointments the appointments to set
     */
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    
}
