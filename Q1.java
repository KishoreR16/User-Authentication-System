package Level3;
import java.io.*;  
import java.util.*;
import java.sql.*;
import java.math.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;  
  
  
@WebServlet("/Level3/form")
public class Q1 extends HttpServlet 
{  
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException
    {  
        
        PrintWriter out = response.getWriter(); 
        String field=request.getParameter("field");   
        String query=request.getParameter("query");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password"); 
        if(field==null)
        {
            field="account";
        }
        
        try
        {
            Class.forName("org.postgresql.Driver");
	        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ajax","postgres", "hello");
            PreparedStatement ps=null;
            if(field.equals("username"))
            {
                ps=con.prepareStatement("select * from level3 where username=?"); 
                ps.setString(1,query);
                ResultSet rs=ps.executeQuery();  
                if(rs.next())
                {  
                    out.print("Username already exists");
                }  
                else
                {
                    out.print("Username available"); 
                } 
            }
            else if(field.equals("email"))
            {
                ps=con.prepareStatement("select * from level3 where email=?"); 
                ps.setString(1,query);
                ResultSet rs=ps.executeQuery();  
                if(rs.next())
                {  
                    out.print("Email id already exists");
                }  
                else
                {
                    out.print("Email id available"); 
                } 
            }
            else
            {
                String insertSQL = "INSERT INTO level3 (username,email,password) VALUES (?, ?,?)";
                PreparedStatement st = con.prepareStatement(insertSQL);
                st.setString(1, name);
                st.setString(2, email);
                st.setString(3, password);
                st.executeUpdate();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
}