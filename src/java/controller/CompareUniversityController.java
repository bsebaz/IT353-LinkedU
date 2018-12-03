/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversityDetailDAO;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.University;

/**
 *
 * @author slfx7
 */
@ManagedBean
@RequestScoped
public class CompareUniversityController implements DetailsInterface {

    private University selectedUniversity1;
    private University selectedUniversity2;
    private final UniversityDetailDAO DB = new UniversityDetailDAO();

    public CompareUniversityController() {
        Map<String, String> params = getParamsFromURL();
        String universityId1 = params.get("universityId1");
        String universityId2 = params.get("universityId2");
        try {
            selectedUniversity1 = DB.getUniversity(Integer.parseInt(universityId1));
            selectedUniversity2 = DB.getUniversity(Integer.parseInt(universityId2));
        } catch (SQLException e) {
            System.out.println("Couldn't find one or more requested universities");
        } catch (NumberFormatException e) {
            System.out.println("Couldn't find one or more requested universities");
        }
    }

    public boolean renderTable() {
        return !(selectedUniversity1 == null || selectedUniversity2 == null);
    }

    /**
     * @return the selectedUniversity1
     */
    public University getSelectedUniversity1() {
        return selectedUniversity1;
    }

    /**
     * @param selectedUniversity1 the selectedUniversity1 to set
     */
    public void setSelectedUniversity1(University selectedUniversity1) {
        this.selectedUniversity1 = selectedUniversity1;
    }

    /**
     * @return the selectedUniversity2
     */
    public University getSelectedUniversity2() {
        return selectedUniversity2;
    }

    /**
     * @param selectedUniversity2 the selectedUniversity2 to set
     */
    public void setSelectedUniversity2(University selectedUniversity2) {
        this.selectedUniversity2 = selectedUniversity2;
    }
}
