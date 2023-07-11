package Level3;
import java.io.*;  
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.*;  
import java.util.*;
import java.sql.*;
@WebServlet("/Level3/Logout")
public class Logout extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try{
		HttpSession session=request.getSession();
		session.removeAttribute("emailandpassword");
		session.invalidate();
		response.sendRedirect("Q1.html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
