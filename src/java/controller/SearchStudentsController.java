/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SearchStudentsDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Student;

/**
 *
 * @author Bailey
 */
@ManagedBean
@SessionScoped
public class SearchStudentsController {
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
    
    public List<Student> renderStudentList() throws SQLException{
        
        SearchStudentsDAO searchStudentDAO = new SearchStudentsDAO();    // Creating a new object each time.
        List students = searchStudentDAO.getStudents(searchTerm);
        
        return students;
    }
}
