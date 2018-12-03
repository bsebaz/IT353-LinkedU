/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ApplicationFormDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ApplicationForm;

/**
 *
 * @author jftur
 */
@ManagedBean
@SessionScoped
public class ApplicationFormController implements java.io.Serializable{
    
    private ApplicationForm applicationForm;
    private final ApplicationFormDAO applicationFormDAO;
    
    public ApplicationFormController(){
        applicationForm = new ApplicationForm();
        applicationFormDAO = new ApplicationFormDAO();
    }

    
    public String openApplicationForm(){
        return "applicationFormEditor.xhtml";
    }
    
    public void saveApplicationForm(){
    
    }
    
    public ApplicationForm getApplicationForm(){return applicationForm;}
    public void setApplicationForm(ApplicationForm applicationForm){this.applicationForm = applicationForm;}
}
