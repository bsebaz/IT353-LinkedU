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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.University;
import model.UniversityDetails;

/**
 *
 * @author slfx7
 */
public class UniversityDetailDAO implements DAOInterface, java.io.Serializable {

    public int addNewDetail(University university) {
        int rowCount = 0;
        try (Connection db = connect()) {

            int universityId = university.getUniversityId();

            String query = "INSERT INTO LinkedUDB.universityDetails (universityId) VALUES (?)";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, universityId);
            rowCount = pstmt.executeUpdate();

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public int updateUniversity(University university) {

        int rowCount = 0;
        try (Connection db = connect()) {

            int universityId = university.getUniversityId();
            String name = university.getName().trim();
            String cost = university.getCost().trim();
            String city = university.getCity().trim();
            String state = university.getState().trim();
            String studentPopulation = university.getStudentPopulation().trim();
            String applicationUrl = university.getApplicationUrl();
            boolean featured = university.isFeatured();

            boolean valid = true;

            if (name.length() < 2 || name.length() > 48) {
                valid = false;
            }
            if (cost.length() < 2 || cost.length() > 25) {
                valid = false;
            }
            if (city.length() < 2 || city.length() > 25) {
                valid = false;
            }
            if (state.length() < 2 || state.length() > 25) {
                valid = false;
            }
            if (studentPopulation.length() < 2 || studentPopulation.length() > 25) {
                valid = false;
            }

            if (valid) {

                String query = "UPDATE LinkedUDB.universities SET name = ?, cost = ?, city = ?, state = ?, studentPopulation = ?, applicationUrl = ?, featured = ? WHERE universityId = ?";
                PreparedStatement pstmt = null;

                pstmt = db.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, cost);
                pstmt.setString(3, city);
                pstmt.setString(4, state);
                pstmt.setString(5, studentPopulation);
                pstmt.setString(6, applicationUrl);
                pstmt.setBoolean(7, featured);
                pstmt.setInt(8, universityId);
                rowCount = pstmt.executeUpdate();

                if (rowCount == 1) {
                    FacesContext.getCurrentInstance().addMessage("universityEdit:success", new FacesMessage("Profile updated successfully"));
                } else {
                    FacesContext.getCurrentInstance().addMessage("universityEdit:error", new FacesMessage("Error updating profile"));
                }

                db.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public int updateUniversityDetails(List<UniversityDetails> universityDetails) {

        int rowCount = 0;
        try (Connection db = connect()) {

            for (UniversityDetails universityDetail : universityDetails) {
                int detailId = universityDetail.getDetailId();
                String detailType = universityDetail.getDetailType();
                String detailName = universityDetail.getDetailName();
                String detailContent = universityDetail.getDetailContent();

                String query = "UPDATE LinkedUDB.universityDetails SET detailType = ?, detailName = ?, detailContent = ? WHERE detailID = ?";
                PreparedStatement pstmt = null;

                pstmt = db.prepareStatement(query);
                pstmt.setString(1, detailType);
                pstmt.setString(2, detailName);
                pstmt.setString(3, detailContent);
                pstmt.setInt(4, detailId);
                rowCount = pstmt.executeUpdate();

                if (rowCount == 1) {
                    FacesContext.getCurrentInstance().addMessage("studentEdit:success", new FacesMessage("Profile updated successfully"));
                } else {
                    FacesContext.getCurrentInstance().addMessage("studentEdit:error", new FacesMessage("Error updating profile"));
                    break;
                }
            }

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public int removeDetail(int detailId) {
        int rowCount = 0;
        try (Connection db = connect()) {

            String query = "DELETE FROM LinkedUDB.universityDetails WHERE detailId = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, detailId);
            rowCount = pstmt.executeUpdate();

            db.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rowCount;
    }

    public University getUniversity(int id) throws SQLException {
        University university = null;

        try (Connection db = connect()) {
            String query = "SELECT * FROM LinkedUDB.universities WHERE UNIVERSITYID = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                university = new University(
                        rs.getInt("universityId"),
                        rs.getInt("accountId"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("studentPopulation"),
                        rs.getString("cost"),
                        rs.getString("accentColor"),
                        rs.getBoolean("featured"),
                        rs.getString("applicationUrl")
                );
            }
            rs.close();
            pstmt.close();
            db.close();
        }
        return university;
    }

    public University getUniversityByUser(int id) throws SQLException {
        University university = null;

        try (Connection db = connect()) {
            String query = "SELECT * FROM LinkedUDB.universities WHERE ACCOUNTID = ?";
            PreparedStatement pstmt = null;

            pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                university = new University(
                        rs.getInt("universityId"),
                        rs.getInt("accountId"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("studentPopulation"),
                        rs.getString("cost"),
                        rs.getString("accentColor"),
                        rs.getBoolean("featured"),
                        rs.getString("applicationUrl")
                );
            }
            rs.close();
            pstmt.close();
            db.close();
        }
        return university;
    }

    public List<UniversityDetails> getUniversityDetails(int universityId) throws SQLException {

        List<UniversityDetails> universityDetails = new ArrayList<UniversityDetails>();

        try (Connection DBConn = connect()) {
            String insertString = "";

            PreparedStatement pstmt = null;

            insertString = "SELECT * FROM LinkedUDB.universityDetails WHERE UNIVERSITYID = ?";

            pstmt = DBConn.prepareStatement(insertString);
            pstmt.setInt(1, universityId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UniversityDetails universityDetail = new UniversityDetails(
                        rs.getInt("detailId"),
                        rs.getInt("universityId"),
                        rs.getString("detailType"),
                        rs.getString("detailName"),
                        rs.getString("detailContent"));

                universityDetails.add(universityDetail);
            }

            DBConn.close();
        }

        return universityDetails;
    }
}
