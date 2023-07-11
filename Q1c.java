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
  
  
@WebServlet("/Level3/login")
public class Q1c extends HttpServlet 
{  
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException
    {  
        
        PrintWriter out = response.getWriter(); 
        String email=request.getParameter("emailloginname");
        String password=request.getParameter("passwordloginname"); 
            try
            {
                Class.forName("org.postgresql.Driver");
	            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ajax","postgres", "hello");
                PreparedStatement ps=con.prepareStatement("select * from level3 where email=? AND password=?"); 
                ps.setString(1,email);
                ps.setString(2,password);
                ResultSet rs=ps.executeQuery(); 
                System.out.print(email+" check "+password);
                response.setContentType("text/html");  
                
                if(rs.next())
                {  
                    String email2=rs.getString("email");
                    String password2=rs.getString("password");
                    System.out.print("in jsp");
                   response.sendRedirect("welcome.jsp"); 
                }  
                else
                {
                    out.print("Not available"); 
                } 
                  
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }  
       
    }
}