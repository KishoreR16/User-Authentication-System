package Level3;
import java.io.*;  
import java.util.*;
import java.sql.*;
import java.math.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;  
  
  
@WebServlet("/Level3/change")
public class Q1a extends HttpServlet 
{  
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException
    {  
        
        PrintWriter out = response.getWriter(); 
        String email=request.getParameter("email");
        String otp=request.getParameter("otp");
        String password=request.getParameter("password"); 
        HttpSession session=request.getSession();  
        String n=(String)session.getAttribute("emailandpassword"); 
        System.out.println("email and password "+n); 
        String otpcheck=n.substring(0, 6);
        String emailcheck=n.substring(6,n.length());
        System.out.println("email and password "+n);
        System.out.println(email+" "+otp+" "+otpcheck+" "+emailcheck);
        if((otp.equals(otpcheck))&&(email.equals(emailcheck)))
        {
            try
            {
                Class.forName("org.postgresql.Driver");
	            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ajax","postgres", "hello");
                
                String insertSQL = "UPDATE level3 SET password=? WHERE EMAIL=?";
                PreparedStatement st = con.prepareStatement(insertSQL);
                st.setString(1, password);
                st.setString(2, email);
                st.executeUpdate();
                out.print("Password has been changed Successfully");
                  
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }  
        }
        else
        {
            out.print("OTP doesn't match");
        }
    }
}