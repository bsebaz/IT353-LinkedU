/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author slfx7
 */
public class Appointment {

    private int appointmentId;
    private University university;
    private Student student;
    private Date start;
    private Date end;
    private Date date;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");

    public Appointment(int appointmentId, University university, Date start, Date end, Date date) {
        this.appointmentId = appointmentId;
        this.university = university;
        this.start = start;
        this.end = end;
        this.date = date;
    }

    public Appointment(int appointmentId, University university, Student student, Date start, Date end, Date date) {
        this(appointmentId, university, start, end, date);
        this.student = student;
    }

    public String getFormattedStart() {
        return dateFormat.format(start);
    }

    public String getFormattedEnd() {
        return dateFormat.format(end);
    }

    /**
     * @return the appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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
}
