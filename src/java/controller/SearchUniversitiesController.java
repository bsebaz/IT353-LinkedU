/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SearchUniversitiesDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.University;

/**
 *
 * @author Bailey
 */
@ManagedBean
@SessionScoped
public class SearchUniversitiesController {

    private String searchTerm;
    private Map<Integer, Boolean> checked;
    private List<University> universities;
    SearchUniversitiesDAO searchUniversitiesDAO;

    public SearchUniversitiesController() {
        searchUniversitiesDAO = new SearchUniversitiesDAO();
        checked = new HashMap();
        try {
            universities = searchUniversitiesDAO.getUniversities(searchTerm);
        } catch (SQLException e) {
            System.out.println("Unable to load universities");
        }
    }

    public void renderUniversitiesList() throws SQLException {
        universities = searchUniversitiesDAO.getUniversities(searchTerm);
        searchTerm = "";
    }

    public String compare() {
        int[] selectedIDs = new int[2];
        int added = 0;
        for (Entry<Integer, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                selectedIDs[added++] = entry.getKey();
                if (added == 2) {
                    break;
                }
            }
        }
        checked.clear();
        return "compareUniversities?faces-redirect=true&universityId1=" + selectedIDs[0] + "&universityId2=" + selectedIDs[1];
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
     * @return the selected
     */
    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    /**
     * @param selected the selected to set
     */
    public void setChecked(Map<Integer, Boolean> selected) {
        this.checked = selected;
    }

    /**
     * @return the universities
     */
    public List<University> getUniversities() {
        return universities;
    }

    /**
     * @param universities the universities to set
     */
    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }
}
