/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ApplicationForm;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jftur
 */
public class ApplicationFormDAO implements DAOInterface{

    private static final String APPLICATION_FORM_QUERY_CONTENT = "SELECT * FROM LinkedUDB.applicationForms WHERE universityId = ? AND applicationId = ?";
    private static final String APPLICATION_FORM_QUERY = "SELECT applicationId FROM LinkedUDB.applicationForms WHERE universityId = ?";
    private static final String APPLICATION_FORM_UPDATE = "UPDATE LinkedUDB.applicationForms SET title = ?, content = ? WHERE universityId = ? AND applicationId = ?";
    private static final String APPLICATION_FORM_INSERT = "INSERT INTO LinkedUDB.applicationForms(universityId, applicationId, title, content) VALUES(?,?,?)";
    private static final String APPLICATION_FORM_DELETE = "";
    
    public ArrayList <String> getUniversityApplicationForms(String universityId){
        ArrayList <String> apps = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection DBConn = null;
        try{
            DBConn = connect();
            String insertString = "";
            insertString = APPLICATION_FORM_QUERY + "";
            pstmt = DBConn.prepareStatement( insertString );
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                apps.add(rs.getString("applicationId"));
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
                pstmt.close();
                rs.close();
                DBConn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                return apps;
            }
        }
    }
    
    public ApplicationForm getApplicationForm(String universityId, String applicationId) throws SQLException{
        ApplicationForm app = new ApplicationForm();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection DBConn = null;
        try{
            DBConn = connect();
            String insertString = "";
            insertString = APPLICATION_FORM_QUERY_CONTENT + "";
            pstmt = DBConn.prepareStatement( insertString );
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                app.setUniversityId(rs.getString("universityId"));
                app.setApplicationId(rs.getString("applicationId"));
                app.setTitle(rs.getString("title"));
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
                pstmt.close();
                rs.close();
                DBConn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                    return app;
            }
        }
        
    }
    
    public JSONObject getApplicationFormJSON(String uiversityID, String applicationID){
        return null;
    }
    
    public void updateApplicationForm(ApplicationForm appForm){
        Connection DBConn = null;
        String insertString = "";
        PreparedStatement pstmt = null;
        insertString = APPLICATION_FORM_QUERY + "";
        try {
            DBConn = connect();
            pstmt = DBConn.prepareStatement( insertString );
            ResultSet rs = pstmt.executeQuery();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
                pstmt.close();
                DBConn.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }finally {return;}
        }
    }
    
    public void insertApplicationForm(ApplicationForm appForm){
        if(appForm == null) return;
        try{
            Connection DBConn = connect();
            String insertString = APPLICATION_FORM_UPDATE + "";
            PreparedStatement pstmt = null;

                pstmt = DBConn.prepareStatement( insertString );
                pstmt.setString( 1, appForm.getUniversityId());
                pstmt.setString( 2, appForm.getApplicationId());
                pstmt.setString( 3, appForm.getTitle());
                
            pstmt.executeUpdate();
            DBConn.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return;
        }
    }
    
    public void deleteApplicationForm(String uiversityId, String applicationId){
        
    }
    
}
