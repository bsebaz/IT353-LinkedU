/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author jftur
 */
@ManagedBean
@SessionScoped
public class ApplicationFormController implements java.io.Serializable{
    
    public String openApplicationForm(){
        return "applicationFormEditor.xhtml";
    }
    
    public void saveApplicationForm(){
    
    }
}
