/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.University;

/**
 *
 * @author slfx7
 */
@ManagedBean
@RequestScoped
public class CompareUniversityController {
    private University selectedUniversity1;
    private University selectedUniversity2;

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
    
    public String compare(University selectedUniversity1, University selectedUniversity2) {
        this.selectedUniversity1 = selectedUniversity1;
        this.selectedUniversity2 = selectedUniversity2;
        return "compareUniversities.xhtml?faces-redirect";
    }
    
    public boolean renderTable() {
        return !(selectedUniversity1 == null || selectedUniversity2 == null);
    }
}
