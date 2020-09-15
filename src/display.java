import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import java.sql.*;  
@WebServlet("/display")   
public class display extends HttpServlet  
{    
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
      {  
         PrintWriter out = res.getWriter();  
         res.setContentType("text/html");  
         out.println("<html><body>");  
         try 
         {  
        	    String dbURL = "jdbc:mysql:// localhost:3306/"; 
        		   
    	        String dbName = "e-learning"; 
    	        String dbUsername = "root"; 
    	        String dbPassword = "root"; 
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	        Connection con = DriverManager.getConnection(dbURL + dbName, 
    	                                                     dbUsername,  
    	                                                     dbPassword); 
             
             Statement stmt = con.createStatement();  
             ResultSet rs = stmt.executeQuery("select * from course");  
             out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>Sr</th><th>Username</th><th>Course</th><tr>");  
             while (rs.next()) 
             {  
                 String n = rs.getString("username");  
                 String nm = rs.getString("course");  
                 int s = rs.getInt("id");   
                 out.println("<tr><td>" + s + "</td><td>" + n + "</td><td>" + nm + "</td></tr>");   
             }  
             out.println("</table>");  
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
             out.println("error");  
         }  
     }  
 }  