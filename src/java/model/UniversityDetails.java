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
public class UniversityDetails implements java.io.Serializable {
    private int detailId;
    private int universityId;
    private String detailType;
    private String detailName;
    private String detailContent;
    
    public UniversityDetails (){
        
    }
    
    public UniversityDetails(int detailId, int universityId, String detailType, String detailName, String detailContent){
        this.detailId = detailId;
        this.universityId = universityId;
        this.detailType = detailType;
        this.detailName = detailName;
        this.detailContent = detailContent;
    }
    
    public String[] splitDetailContent(){
        return detailContent.split(",");
    }
    
    public String test(){
        return "test";
    }

    /**
     * @return the detailId
     */
    public int getDetailId() {
        return detailId;
    }

    /**
     * @param detailId the detailId to set
     */
    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    /**
     * @return the detailType
     */
    public String getDetailType() {
        return detailType;
    }

    /**
     * @param detailType the detailType to set
     */
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    /**
     * @return the detailName
     */
    public String getDetailName() {
        return detailName;
    }

    /**
     * @param detailName the detailName to set
     */
    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    /**
     * @return the detailContent
     */
    public String getDetailContent() {
        return detailContent;
    }

    /**
     * @param detailContent the detailContent to set
     */
    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
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
    
}
