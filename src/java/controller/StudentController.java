/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Student;

/**
 *
 * @author kchris4
 */

@ManagedBean
@SessionScoped
public class StudentController implements java.io.Serializable{
    
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public StudentController() 
    {
        student = new Student();
    }
    
    public String getResponse()
    {
        String resultString = "";
        
        return resultString;
    }
    
    public void attemptStudentInsert()
    {
       // boolean goodStudentInsert;
        boolean goodAccountInsert = false;
        
        
        //StudentDAO.insertStudent(this.student);
        
        
        
        //return goodAccountInsert;
        
    }
}
