/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.StudentDAO;

/**
 *
 * @author slfx7
 */
public class Student implements java.io.Serializable {
    private int studentId;
    private int userId; //AccountID in table
    private String firstName;
    private String lastName;
    private String age;
    private String school;
    private String graduationYear;
    private String gpa;
    
    public Student (){
        
    }
    
    public Student(int userId, int studentId, String firstName, String lastName, String age, String school, String graduationYear, String gpa){
        this.userId = userId;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.school = school;
        this.graduationYear = graduationYear;
        this.gpa = gpa;
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return the graduationYear
     */
    public String getGraduationYear() {
        return graduationYear;
    }

    /**
     * @param graduationYear the graduationYear to set
     */
    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    /**
     * @return the gpa
     */
    public String getGpa() {
        return gpa;
    }

    /**
     * @param gpa the gpa to set
     */
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public boolean attemptStudentInsert()
    {
        boolean goodStudentInsert;
        
        goodStudentInsert = StudentDAO.insertStudent(this);
        
        return goodStudentInsert;
    }
}
