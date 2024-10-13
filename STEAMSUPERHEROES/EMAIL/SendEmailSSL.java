package com.emailProject;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailSSL {

    public static void main(String[] args) {
        sendEmail("userMentee", "userMentor");
        
    }

    public static void sendEmail(String emailMentee, String emailMentor){
        String usernameMentee = emailMentee;
        String usernameMentor = emailMentor;
        String password = "xeya rxwu lygu ecok";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session1 = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(usernameMentee, password);
                    }
                });
        Session session2 = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(usernameMentor, password);
                    }
                });
            

        try {

            Message messageMentee = new MimeMessage(session1);
            messageMentee.setFrom(new InternetAddress(usernameMentee));
            messageMentee.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(usernameMentee)
            );
            Message messageMentor = new MimeMessage(session2);
            messageMentor.setFrom(new InternetAddress(usernameMentor));
            messageMentor.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(usernameMentor)
            );


            messageMentee.setSubject("Change Schedule");
            messageMentee.setText("Dear Mentee,"
                    + "\n\n Session cannot be made. Please reschedule.");
            
            messageMentor.setSubject("Change Schedule");
                messageMentor.setText("Dear Mentor,"
                    + "\n\n Session cannot be made. Please reschedule.");

            Transport.send(messageMentee);
            Transport.send(messageMentor);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
