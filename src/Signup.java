

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try { 

	
			  
		        String dbURL = "jdbc:mysql:// localhost:3306/"; 
		   
		        String dbName = "e-learning"; 
		        String dbUsername = "root"; 
		        String dbPassword = "root"; 
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection(dbURL + dbName, 
		                                                     dbUsername,  
		                                                     dbPassword); 
			
		        String sql ="INSERT INTO users (username,email,password)"
						+"VALUES (?,?,?)";
				
		        PreparedStatement pstmt = con.prepareStatement(sql); 
				pstmt.setString(1,request.getParameter("name") );
				pstmt.setString(2,request.getParameter("email") );
		        pstmt.setString(3, request.getParameter("pswd"));
		      

		
			int row =pstmt.executeUpdate(); 

		
			pstmt.close(); 
			con.close(); 

		
			if(row>0) {
			PrintWriter out = response.getWriter(); 
			out.println("<html><body><b>Successfully Sign Up!!"
						+ "</b></body></html>"); 
			}
			else
			{
				PrintWriter out = response.getWriter(); 
				out.println("<html><body><b>Some Error Occure Try again"
							+ "</b></body></html>");
				
			}
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	
		
	}

}
