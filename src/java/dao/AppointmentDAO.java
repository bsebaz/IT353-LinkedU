/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Arrays;
import model.Appointment;
import model.Student;
import model.University;

/**
 *
 * @author slfx7
 */
public class AppointmentDAO implements DAOInterface, java.io.Serializable {

    public void createAppointment(String advisor, String start, String end, String date, University university) {
        ArrayList vars = new ArrayList(Arrays.asList(advisor, start, end, date, university.getUniversityId()));
        String sql = "INSERT INTO LinkedUDB.appointments (advisor, startTime, endTime, aptDate, universityId)"
                + "VALUES(?, ?, ?, ?, ?)";
        updateDB(sql, vars);
    }

    public void scheduleAppointment(Appointment appointment, Student student) {
        ArrayList vars = new ArrayList(Arrays.asList(student.getStudentId(), appointment.getAppointmentId()));
        String sql = "UPDATE LinkedUDB.appointments SET STUDENTID = ? WHERE APPOINTMENTID = ?";
        updateDB(sql, vars);
    }

    public void unscheduleAppointment(Appointment appointment) {
        ArrayList vars = new ArrayList(Arrays.asList(appointment.getAppointmentId()));
        String sql = "UPDATE LinkedUDB.appointments SET STUDENTID = null WHERE APPOINTMENTID = ?";
        updateDB(sql, vars);
    }

    public void deleteAppointment(Appointment appointment) {
        ArrayList vars = new ArrayList(Arrays.asList(appointment.getAppointmentId()));
        String sql = "DELETE FROM LinkedUDB.appointments WHERE APPOINTMENTID = ?";
        updateDB(sql, vars);
    }
}
