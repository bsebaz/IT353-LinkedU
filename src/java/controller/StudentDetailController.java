/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDetailDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import model.Student;
import model.StudentDetails;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author slfx7
 */
@ManagedBean
@SessionScoped
public class StudentDetailController implements DetailsInterface, java.io.Serializable {

    private Student student;
    private final StudentDetailDAO DB = new StudentDetailDAO();
    private List<StudentDetails> studentDetails;
    private UploadedFile file;
    @ManagedProperty(value = "#{accountController.user.userID}")
    private int userID;

    @PostConstruct
    public void viewDetails() {
        //Get studentId from URL
        Map<String, String> params = getParamsFromURL();
        String id = params.get("studentId");

        //We'll need more cases than what's here, such as checking if the account is a student / recruiter
        //Get requested user
        if (id != null) {
            try {
                student = DB.getStudent(Integer.parseInt(id));
                if (student != null) {
                    studentDetails = DB.getStudentDetails(student.getStudentId());
                }
            } catch (SQLException | NumberFormatException e) {
                System.out.println("Couldn't find requested user");
                e.getLocalizedMessage();
            }
        } //Otherwise, return the current user's page
        else {
            try {
                student = DB.getStudent(userID);
            } catch (SQLException e) {
                System.out.println("Couldn't find requested user");
            }
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
                
                DB.updateStudentImagePath("image/"+fileName, student.getStudentId());

                System.out.println("New file created!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    public void refreshDetails() {
        try {
            studentDetails = DB.getStudentDetails(student.getStudentId());
        } catch (SQLException e) {
            System.out.println("Couldn't refresh details");
        }
    }

    public String updateStudent() {
        DB.updateStudent(student);
        DB.updateStudentDetails(studentDetails);
        return "studentEdit?studentId=" + student.getStudentId();
    }

    public String addNewDetail() throws SQLException {
        DB.updateStudent(student);
        DB.updateStudentDetails(studentDetails);
        DB.addNewDetail(student);
        return "studentEdit?redirect=true&studentId=" + student.getStudentId();
    }

    public String removeDetail(int detailId) throws SQLException {
        DB.updateStudent(student);
        DB.updateStudentDetails(studentDetails);
        DB.removeDetail(detailId);
        return "studentEdit?redirect=true&studentId=" + student.getStudentId();
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the studentDetails
     */
    public List<StudentDetails> getStudentDetails() {
        return studentDetails;
    }

    /**
     * @param studentDetails the studentDetails to set
     */
    public void setStudentDetails(List<StudentDetails> studentDetails) {
        this.studentDetails = studentDetails;
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
