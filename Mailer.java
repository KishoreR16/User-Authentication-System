package Level3;
import java.io.*;  
import java.util.*;
import java.sql.*;
import java.math.*; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  


public class Mailer {  
public static void send(String to,String subject,String msg){  
  
final String user="hindimoviesbond007@gmail.com";//change accordingly  
final String pass="mfbbylfncriavzsf";  
  
//1st step) Get the session object   
Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.ssl.trust", "*");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.starttls.enable", "true");

	
// Properties props = new Properties();  
	
	// props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.auth", "true");

		// props.put("mail.smtp.host", "smtp.gmail.com"); 
		// props.put("mail.debug", "true");
		
	    // props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	    // props.setProperty("mail.smtp.socketFactory.fallback", "true");   
	    // props.setProperty("mail.smtp.port", "465");   
	    // props.setProperty("mail.smtp.socketFactory.port", "465");   
  
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,pass);  
   }  
});  
//2nd step)compose message  
try {  
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 message.setText(msg);  
   
 //3rd step)send message  
 Transport.send(message);  
  
 System.out.println("Done");  
  
 } catch (MessagingException e) {  
    throw new RuntimeException(e);  
 }  
      
}  
}  