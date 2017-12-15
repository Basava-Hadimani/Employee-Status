import java.io.*;   

import javax.servlet.*;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;    

import java.sql.*;

@WebServlet(value = "/login")  
public class Validation extends HttpServlet 
{    
	static final String DB_DRIVE = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	static final String NAME = "Basavaraj";
	static final String PW = "Basu@480478143";
	String password = null;
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, 
     HttpServletResponse response) 
       throws ServletException, IOException 
   {        
       response.setContentType("text/html");      
       PrintWriter pwriter = response.getWriter(); 
       
       String name=request.getParameter("uname");
       String id=request.getParameter("uid");      
       String pass=request.getParameter("upass");   
       
		Statement stmt = null;
		Connection con = null;
		
		String password = null;
       
		try {

			Class.forName(DB_DRIVE);
			con = DriverManager.getConnection(DB_URL, NAME, PW);
			
			con.setAutoCommit(false);
			
			String sql = "SELECT password FROM detail WHERE empid="+"'"+id+"'"+" AND name="+"'"+name+"'"+";";
			
        	stmt = con.createStatement();
        	
        	//stmt.setString(1, id);
        //	stmt.setString(2,name);
			
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next())
			{
				password = rs.getString("password");
			}
			
			con.commit();
			// rs.close();
	        stmt.close();
	        con.close();
	        
 			} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
		   }//end tr
       
       if(pass.equals(password))
       {          
          RequestDispatcher dis=request.getRequestDispatcher("welcome");          
          dis.forward(request, response);      
       }     
       else
       {     
    	  
          pwriter.print("<center><font color=\"red\">PASSWORD IS NOT CORRECT OR NOT REGISTERED</font></center>");          
          RequestDispatcher dis=request.getRequestDispatcher("index.html");          
          dis.include(request, response);                                
       }      
   }    
} 