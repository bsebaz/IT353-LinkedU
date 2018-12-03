/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.xml.registry.infomodel.Concept;
import model.Appointment;
import model.Student;
import model.University;

/**
 *
 * @author slfx7
 */
public class AppointmentDAO implements DAOInterface, java.io.Serializable {

    public void createAppointment(Time start, Time end, Date date, University university) {
        ArrayList vars = new ArrayList(Arrays.asList(start, end, date, university.getUniversityId()));
        String sql = "INSERT INTO LinkedUDB.appointments (startTime, endTime, aptDate, universityId)"
                + "VALUES(?, ?, ?, ?)";
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

    public ArrayList<Appointment> getUnscheduledAppointments(University university) {
        ArrayList<Appointment> appointmentCollection = new ArrayList();
        String query = "SELECT * FROM LinkedUDB.appointments "
                + "WHERE UNIVERSITYID = ? AND "
                + "STUDENTID IS NULL";

        try (Connection db = connect()) {
            PreparedStatement stmt = db.prepareStatement(query);
            stmt.setInt(1, university.getUniversityId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointmentCollection.add(new Appointment(
                        rs.getInt("APPOINTMENTID"),
                        university,
                        rs.getTime("STARTTIME"),
                        rs.getTime("ENDTIME"),
                        rs.getDate("APTDATE")
                ));
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            System.err.println("Could Not Get Unscheduled Appointments");
            e.printStackTrace();
        }
        return appointmentCollection;
    }

    public ArrayList<Appointment> getScheduledAppointments(University university) {
        ArrayList<Appointment> appointmentCollection = new ArrayList();
        StudentDetailDAO studentDB = new StudentDetailDAO();
        String query = "SELECT * FROM LinkedUDB.appointments "
                + "WHERE UNIVERSITYID = ? AND "
                + "STUDENTID IS NOT NULL";

        try (Connection db = connect()) {
            PreparedStatement stmt = db.prepareStatement(query);
            stmt.setInt(1, university.getUniversityId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointmentCollection.add(new Appointment(
                        rs.getInt("APPOINTMENTID"),
                        university,
                        studentDB.getStudent(rs.getInt("STUDENTID")),
                        rs.getTime("STARTTIME"),
                        rs.getTime("ENDTIME"),
                        rs.getDate("APTDATE")
                ));
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            System.err.println("Could Not Get Scheduled Appointments");
            e.printStackTrace();
        }
        return appointmentCollection;
    }

    public ArrayList<Appointment> getStudentAppointments(Student student) {
        ArrayList<Appointment> appointmentCollection = new ArrayList();
        UniversityDetailDAO universityDB = new UniversityDetailDAO();
        String query = "SELECT * FROM LinkedUDB.appointments "
                + "WHERE STUDENTID = ?";

        try (Connection db = connect()) {
            PreparedStatement stmt = db.prepareStatement(query);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointmentCollection.add(new Appointment(
                        rs.getInt("APPOINTMENTID"),
                        universityDB.getUniversity(rs.getInt("UNIVERSITYID")),
                        student,
                        rs.getTime("STARTTIME"),
                        rs.getTime("ENDTIME"),
                        rs.getDate("APTDATE")
                ));
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            System.err.println("Could Not Get Unscheduled Appointments");
            e.printStackTrace();
        }
        return appointmentCollection;
    }
}
