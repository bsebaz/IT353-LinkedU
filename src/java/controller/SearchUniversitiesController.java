/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SearchUniversitiesDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<University> renderUniversitiesList() throws SQLException{
        
        SearchUniversitiesDAO searchUniversitiesDAO = new SearchUniversitiesDAO();    // Creating a new object each time.
        List universities = searchUniversitiesDAO.getUniversities(searchTerm);
        
        return universities;
    }
}
