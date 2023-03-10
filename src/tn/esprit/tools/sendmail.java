/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ghof
 */



public class sendmail {
//     public static void main(String[] args) throws IOException, GeneralSecurityException {
//         sendmail se =new sendmail();
//         se.sendEmail("raed.gabsi@esprit.tn", "code","vsf");
//     }
//   
   
    public static void sendEmail(String SendTo ,String Subject,String msg) throws GeneralSecurityException
    {
        
        String to = "rayenalmi64@gmail.com" ; 

        
        String from = "rayenalmi65@gmail.com";

        
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        
        properties.put("mail.smtp.host", host);
     
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
properties.put("mail.smtp.ssl.socketFactory", sf);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.starttls.required", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("ugoapp4@gmail.com", "zfkbeesjbljfcnun");

            }

        });

   
        session.setDebug(true);

        try {
    
            MimeMessage message = new MimeMessage(session);
           
            message.setFrom(new InternetAddress(from));
       
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(SendTo));
         
            message.setSubject(Subject);

            message.setText(msg);

            System.out.println("sending...");
          
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}

