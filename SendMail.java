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

@WebServlet("/Level3/mail")
public class SendMail extends HttpServlet {  
public void doGet(HttpServletRequest request,  
 HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
      
    String to=request.getParameter("to");  
    String subject=request.getParameter("subject");  
    String msg=request.getParameter("msg");  
          
    Mailer.send(to, subject, msg);  
    out.print("<script>alert(\"Message sent Successfully\");</script>");
    
	response.sendRedirect("mail.html"); 
	out.println("checking");
    }  
  
}  