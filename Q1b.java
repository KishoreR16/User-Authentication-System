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
import javax.servlet.http.HttpSession;  
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

@WebServlet("/Level3/mailing")
public class Q1b extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException
	{
		PrintWriter out = response.getWriter(); 
		String to=request.getParameter("email");
		Random rnd = new Random();
	    int i = rnd.nextInt(999999); 
	    String msg=String.valueOf(i);   
		String subject="OTP details";
		String session2;
		HttpSession session1=request.getSession();  
		session2=(String)msg+to;
        session1.setAttribute("emailandpassword",session2);  
		System.out.println("email and password "+session2);
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
		System.out.println("1");    
		Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{  
				 
   				return new PasswordAuthentication(user,pass);
				 
   			}  
		});  
//2nd step)compose message  
		try
		{  
			  
 			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(user));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			message.setSubject(subject);  
			message.setText(msg);  
			 
			//3rd step)send message  
			Transport.send(message);  
			  
			out.print("OTP sent to your email");
		} 
		catch (MessagingException e) 
		{  
			throw new RuntimeException(e);  
		}  
	}  
}  