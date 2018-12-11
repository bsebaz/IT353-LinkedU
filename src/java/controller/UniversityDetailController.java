/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversityDetailDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import model.University;
import model.UniversityDetails;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class UniversityDetailController implements DetailsInterface, java.io.Serializable {

    private University university;
    private final UniversityDetailDAO DB = new UniversityDetailDAO();
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;
    private List<UniversityDetails> universityDetails;
    private UploadedFile file;

    @PostConstruct
    public void viewDetails() {
        //Get universityId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("universityId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested university
        if (id != null) {
            try {
                university = DB.getUniversity(Integer.parseInt(id));
                if (university != null) {
                    universityDetails = DB.getUniversityDetails(university.getUniversityId());
                }
            } catch (SQLException e) {
                System.out.println("Couldn't find requested university");
            } catch (NumberFormatException e) {
                System.out.println("Couldn't find requested university");
                e.getLocalizedMessage();
            }
        } //Otherwise, return the current user's page
        else if (userID > 0) {
            try {
                university = DB.getUniversity(userID);
            } catch (SQLException e) {
                System.out.println("Couldn't find requested university");
            }
        }
    }

    public void refreshDetails() {
        try {
            universityDetails = DB.getUniversityDetails(university.getUniversityId());
        } catch (SQLException e) {
            System.out.println("Couldn't refresh details");
        }
    }
    
    public void upload() throws IOException, MessagingException {
        try (InputStream input = file.getInputstream()) {
            String fileName = file.getFileName();

            try {
                // write the inputStream to a FileOutputStream
                System.out.println(System.getProperty("user.dir"));
                
                //File path is defined locally, must change to location of image folder within project.
                OutputStream out = new FileOutputStream(new File("C:\\Users\\Bailey\\Documents\\GitHub\\IT353-LinkedU\\web\\resources\\image\\" + fileName));

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = input.read(/*bytes*/)) != -1) {
                    out.write(read);//bytes, 0, read);
                }

                input.close();
                out.flush();
                out.close();
                
                DB.updateUniversityImagePath("image/"+fileName, university.getUniversityId());

                System.out.println("New file created!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }    

    public String updateUniversity() {
        DB.updateUniversity(university);
        DB.updateUniversityDetails(universityDetails);
        return "universityEdit?universityId=" + university.getUniversityId();
    }

    public String addNewDetail() throws SQLException {
        DB.updateUniversity(university);
        DB.updateUniversityDetails(universityDetails);
        DB.addNewDetail(university);
        return "universityEdit?redirect=true&universityId=" + university.getUniversityId();
    }

    public String removeDetail(int detailId) throws SQLException {
        DB.updateUniversity(university);
        DB.updateUniversityDetails(universityDetails);
        DB.removeDetail(detailId);
        return "universityEdit?redirect=true&universityId=" + university.getUniversityId();
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }

    /**
     * @return the universityDetails
     */
    public List<UniversityDetails> getUniversityDetails() {
        return universityDetails;
    }

    /**
     * @param universityDetails the universityDetails to set
     */
    public void setUniversityDetails(List<UniversityDetails> universityDetails) {
        this.universityDetails = universityDetails;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
