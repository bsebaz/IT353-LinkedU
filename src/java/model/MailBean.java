/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BillyLim Code snippet courtesy of your fellow student Dinesh Daultani
 */
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailBean {

    public static void sendEmail(Appointment appt, Student stud) {
        final String username = "LinkedU353@gmail.com";
        final String password = "IT353L!nked";

        // Get system properties
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    //Replace this with stu.email?
                    InternetAddress.parse("mmchug1@outlook.com"));
            message.setSubject("Appointment Confirmation");
            message.setText("Dear " + stud.getFirstName() + " " + stud.getLastName() + ", "
                    + "\n\nThis is an email confirmation of you appointment at " + appt.getUniversity().getName() + " on "
                    + appt.getFormattedDate() + " from " + appt.getFormattedStart() + " to " + appt.getFormattedEnd() + ". "
                    + "Please be sure to arrive on time and dress appropriately.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
