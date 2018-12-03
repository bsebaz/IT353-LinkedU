/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author slfx7
 */
public class University implements java.io.Serializable {

    private int universityId;
    private String name;
    private String city;
    private String state;
    private String studentPopulation;
    private String cost;
    private String accentColor;
    private boolean featured;

    public University() {

    }

    public University(int universityId, String name, String city, String state, String studentPopulation, String cost) {
        this.universityId = universityId;
        this.name = name;
        this.city = city;
        this.state = state;
        this.studentPopulation = studentPopulation;
        this.cost = cost;
    }

    public University(int universityId, String name, String city, String state, String studentPopulation, String cost, String accentColor, boolean featured) {
        this(universityId, name, city, state, studentPopulation, cost);
        this.accentColor = accentColor;
        this.featured = featured;
    }

    /**
     * @return the universityId
     */
    public int getUniversityId() {
        return universityId;
    }

    /**
     * @param universityId the universityId to set
     */
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the studentPopulation
     */
    public String getStudentPopulation() {
        return studentPopulation;
    }

    /**
     * @param studentPopulation the studentPopulation to set
     */
    public void setStudentPopulation(String studentPopulation) {
        this.studentPopulation = studentPopulation;
    }

    /**
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * @return the featured
     */
    public boolean isFeatured() {
        return featured;
    }

    /**
     * @param featured the featured to set
     */
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    /**
     * @return the accentColor
     */
    public String getAccentColor() {
        return accentColor;
    }

    /**
     * @param accentColor the accentColor to set
     */
    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

}
