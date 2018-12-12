/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.University;

/**
 *
 * @author kchris4
 */
public class UniversityDAO implements DAOInterface, java.io.Serializable {

    public String getUniversityName(int userID) {
        String query = "SELECT NAME FROM LINKEDUDB.UNIVERSITIES WHERE ACCOUNTID = ?";
        String name = "Profile";
        try (Connection db = connect()) {
            PreparedStatement pstmt = null;
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("NAME");
            }
            rs.close();
            pstmt.close();
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to get university's name");
            System.out.println(e.getLocalizedMessage());
        }
        return name;
    }

    public int getUniversityID(int userID) {
        String query = "SELECT UNIVERSITYID FROM LINKEDUDB.UNIVERSITIES WHERE ACCOUNTID = ?";
        int id = -1;
        try (Connection db = connect()) {
            PreparedStatement pstmt = null;
            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("UNIVERSITYID");
            }
            rs.close();
            pstmt.close();
            db.close();
        } catch (SQLException e) {
            System.out.println("Failed to get university's name");
            System.out.println(e.getLocalizedMessage());
        }
        return id;
    }

    public int insertUniversity(University university, int accountID) {
        int universityID = -1;
        ArrayList vars = new ArrayList(Arrays.asList(accountID, university.getName(), university.getCity(), university.getState(), university.getAccentColor(), false));
        String query = "INSERT INTO LINKEDUDB.UNIVERSITIES (ACCOUNTID, NAME, CITY, STATE, ACCENTCOLOR, FEATURED) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection db = connect()) {
            PreparedStatement stmt = db.prepareStatement(query, new String[]{"UNIVERSITYID"});
            setVars(stmt, vars);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                universityID = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: unable to insert user");
            return -1;
        }
        return universityID;
    }
}
