/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Student;

/**
 *
 * @author kchris4
 */
public class StudentController implements java.io.Serializable{

    private Student student;
    
    public StudentController()
    {
        student = new Student();
    }

    public Student getModel()
    {
        return student;
    }

    public void setModel(Student model)
    {
        this.student = model;
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
