/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDetailDAO;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Student;

/**
 *
 * @author slfx7
 */
@ManagedBean
@RequestScoped
public class CompareStudentsController implements DetailsInterface {

    private Student selectedStudent1;
    private Student selectedStudent2;
    private final StudentDetailDAO DB = new StudentDetailDAO();

    public CompareStudentsController() {
        Map<String, String> params = getParamsFromURL();
        String studentId1 = params.get("studentId1");
        String studentId2 = params.get("studentId2");
        try {
            selectedStudent1 = DB.getStudent(Integer.parseInt(studentId1));
            selectedStudent2 = DB.getStudent(Integer.parseInt(studentId2));
        } catch (SQLException e) {
            System.out.println("Couldn't find one or more requested students");
        } catch (NumberFormatException e) {
            System.out.println("Couldn't find one or more requested students");
        }
    }

    public boolean renderTable() {
        return !(selectedStudent1 == null || selectedStudent2 == null);
    }

    /**
     * @return the selectedStudent1
     */
    public Student getSelectedStudent1() {
        return selectedStudent1;
    }

    /**
     * @param selectedStudent1 the selectedStudent1 to set
     */
    public void setSelectedStudent1(Student selectedStudent1) {
        this.selectedStudent1 = selectedStudent1;
    }

    /**
     * @return the selectedStudent2
     */
    public Student getSelectedStudent2() {
        return selectedStudent2;
    }

    /**
     * @param selectedStudent2 the selectedStudent2 to set
     */
    public void setSelectedStudent2(Student selectedStudent2) {
        this.selectedStudent2 = selectedStudent2;
    }
}
