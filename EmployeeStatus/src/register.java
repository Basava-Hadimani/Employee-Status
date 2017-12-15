import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/store")
public class register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String DB_DRIVE = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	static final String NAME = "Basavaraj";
	static final String PW = "Basu@480478143";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {

			PreparedStatement stmt = null;
			Connection con = null;
			
 	 		response.setContentType("text/html"); 
 	 		PrintWriter out = response.getWriter();
 	 	    String name = request.getParameter("uname"); 
 	 	    String id = request.getParameter("uid");
 	 	    String password = request.getParameter("upass1");
 	 	    String gender = request.getParameter("gen");
 	 	    String mail = request.getParameter("mailid");
 	 	    try {
 			
 			

 			
				Class.forName(DB_DRIVE);
				con = DriverManager.getConnection(DB_URL, NAME, PW);
				
				con.setAutoCommit(false);
				
				String sql = "INSERT INTO detail VALUES (?,?,?,?,?)";
	        	stmt = con.prepareStatement(sql);
	        	
	        	stmt.setString(1,name);
	        	stmt.setString(2,id);
	        	stmt.setString(3,password);
	        	stmt.setString(4,gender);
	        	stmt.setString(5,mail);
				
				stmt.executeUpdate();
				
				con.commit();
				
				out.println("<center><font color=\"green\">Registration is completed successfully</p></center>");
		        RequestDispatcher dis=request.getRequestDispatcher("index.html");          
		        dis.include(request, response); 
				// rs.close();
		        stmt.close();
		        con.close();
		        
	 			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				out.println("<center><font color=\"red\">Already registered</center>");
		        RequestDispatcher dis=request.getRequestDispatcher("index1.html");          
		        dis.include(request, response); 
	 			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				out.println("<center><font color=\"red\">Error occured!</center>");
		        RequestDispatcher dis=request.getRequestDispatcher("index1.html");          
		        dis.include(request, response); 
	 			}
 	 	    	finally{
		      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(con!=null)
			            con.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
		}
	
	
}
