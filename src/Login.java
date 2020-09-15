

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 

			// Initialize the database 
			  
		        String dbURL = "jdbc:mysql:// localhost:3306/"; 
		        // Database name to access 
		        String dbName = "e-learning"; 
		        String dbUsername = "root"; 
		        String dbPassword = "root"; 
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection(dbURL + dbName, 
		                                                     dbUsername,  
		                                                     dbPassword); 
			// Create a SQL query to insert data into demo table 
			// demo table consists of two columns, so two '?' is used 
			PreparedStatement st = con 
				.prepareStatement("SELECT count(*) FROM users WHERE username=? and password=?"); 

			// For the first parameter, 
			// get the data using request object 
			// sets the data to st pointer 
			st.setString(1, request.getParameter("name")); 

			// Same for second parameter 
			st.setString(2, request.getParameter("pswd")); 

			// Execute the insert command using executeUpdate() 
			// to make changes in database 
			ResultSet rs =st.executeQuery(); 
			 PrintWriter out = response.getWriter(); 
			while(rs.next()) {
				  if(rs.getInt("count(*)")>0)
		            {
		              
		               
					  PrintWriter out1 = response.getWriter(); 
						out1.println("<html><body><b>Successfully Login!!"
									+ "</b></body></html>"); 
		            } else {
		               
		          
		            
						out.println("<html><body><b>Invalid creadentials!!!"
									+ "</b></body></html>");
						
		            }
			}

			// Close all the connections 
			st.close(); 
			con.close(); 

			// Get a writer pointer 
			// to display the successful result 
			
			
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	
	}

}
