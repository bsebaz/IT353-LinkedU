/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SearchStudentsDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Student;

/**
 *
 * @author Bailey
 */
@ManagedBean
@SessionScoped
public class SearchStudentsController implements java.io.Serializable {

    private String searchTerm;
    private Map<Integer, Boolean> checked;
    private List<Student> students;
    SearchStudentsDAO searchStudentsDAO;

    public SearchStudentsController() {
        searchStudentsDAO = new SearchStudentsDAO();
        checked = new HashMap();
        try {
            students = searchStudentsDAO.getStudents(searchTerm);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Unable to load students");
        }
    }

    public void renderStudentList() {
        try {
            students = searchStudentsDAO.getStudents(searchTerm);
            searchTerm = "";
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Unable to load students");
        }
    }

    public String compare() {
        int[] selectedIDs = new int[2];
        int added = 0;
        for (Map.Entry<Integer, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                selectedIDs[added++] = entry.getKey();
                if (added == 2) {
                    break;
                }
            }
        }
        checked.clear();
        return "compareStudents?faces-redirect=true&studentId1=" + selectedIDs[0] + "&studentId2=" + selectedIDs[1];
    }

    /**
     * @return the searchTerm
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @param searchTerm the searchTerm to set
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * @return the checked
     */
    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
