import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.String;
import java.sql.*;

@WebServlet(value = "/update") 
public class Update extends HttpServlet  {
	 /**
	 * 
	 */
	static final String DB_DRIVE = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	static final String NAME = "Basavaraj";
	static final String PW = "Basu@480478143";
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			 {
		
				PreparedStatement stmt = null;
				Connection con = null;
				
				
		 		response.setContentType("text/html"); 
		 		PrintWriter out = response.getWriter();
		 		
		 /*	    Calendar cal = Calendar.getInstance();
		 	    cal.add(Calendar.DATE, 1);
		 	    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		 	    String date = format1.format(cal.getTime());*/
		 		
		 		String Date=request.getParameter("date");
		 		String Name=request.getParameter("uname");
		 		String id=request.getParameter("uid");
		 		//String update=request.getParameter("update1");
		 		//String given=request.getParameter("task1");
		 		String charge=request.getParameter("charge");
		 		//String hour=request.getParameter("hour1");
		 		
		 		
		 		String []task = new String[5];
		 		
		 		task[0] = request.getParameter("task1");
		 		task[1] = request.getParameter("task2");
		 		task[2] = request.getParameter("task3");
		 		task[3] = request.getParameter("task4");
		 		task[4] = request.getParameter("task5");
		 		
		 		String []update = new String[5];
		 		
		 		update[0] = request.getParameter("update1");
		 		update[1] = request.getParameter("update2");
		 		update[2] = request.getParameter("update3");
		 		update[3] = request.getParameter("update4");
		 		update[4] = request.getParameter("update5");
		 		
		 		String []hour = new String[5];
		 		
		 		hour[0] = request.getParameter("hour1");
		 		hour[1] = request.getParameter("hour2");
		 		hour[2] = request.getParameter("hour3");
		 		hour[3] = request.getParameter("hour4");
		 		hour[4] = request.getParameter("hour5");
		 		
				try {
					Class.forName(DB_DRIVE);
					con = DriverManager.getConnection(DB_URL, NAME, PW);
					
					con.setAutoCommit(false);
					
			        for (int i=0; i < 5; i++)
			        {
			        	boolean isEmpty = task[i] == null || task[i].trim().length() == 0;
			        	if (isEmpty) {
			        	    break;
			        	}
			        	String sql = "INSERT INTO data VALUES (?,?,?,?,?,?,?)";
			        	stmt = con.prepareStatement(sql);
			        	
			        	stmt.setString(1,Date);
			        	stmt.setString(2,Name);
			        	stmt.setString(3,id);
			        	stmt.setString(4,charge);
			        	stmt.setString(5,task[i]);
			        	stmt.setString(6,update[i]);
			        	stmt.setString(7,hour[i]);
					
					stmt.executeUpdate();
					
					con.commit();
			        }
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
				   }//end try
				
		 		out.println("<p>Daily update is saved successfully</p>");

		        RequestDispatcher dis=request.getRequestDispatcher("welcome");          
		        dis.forward(request, response); 
		 		
		 	/*	FileOutputStream fos = null;
		 	    File file;
		 		String mycontent ="Charge Code : "+charge+"\nTask Given : "+given+"\nTask Update : "+update+"\n---------------------------------------------------------\n";
		 		try {
		 			  file = new File("D:/Java/mytext.txt");
		 			  fos = new FileOutputStream(file, true);
		 			  if (!file.exists()) {
		 			     file.createNewFile();
		 			  }
		 			  
		 			  byte[] bytesArray = mycontent.getBytes();

		 			  fos.write(bytesArray);
		 			  fos.flush();
		 		       } 
		 		       catch (IOException ioe) {
		 			  ioe.printStackTrace();
		 		       } 
		 		       finally {
		 		    	   try {
		 		    		   if (fos != null) 
		 		    		   {
		 		    			   fos.close();
		 		    		   }
		 		    		   out.close(); 
		 		    	   } 
		 		    	   catch (IOException ioe) {
		 		    		   System.out.println("Error in closing the Stream");
		 		    	   }
		 		       }*/
		 /*       File myFile_ED = new File("C://Users//GUR47765//Pictures//EmployeeDetail//TestData.xlsx");
		        FileInputStream inputStream_ED = new FileInputStream(myFile_ED);

		        XSSFWorkbook workbook_ED = new XSSFWorkbook(inputStream_ED);
		        XSSFSheet sheet_ED = workbook_ED.getSheetAt(0);
		        Iterator<Row> riterator_ED = sheet_ED.iterator();
		   //     Row row_ED = sheet_ED.getRow(sheet_ED.getLastRowNum());
		        for (int i=0; i < 5; i++)
		        {
		        	boolean isEmpty = task[i] == null || task[i].trim().length() == 0;
		        	if (isEmpty) {
		        	    break;
		        	}
		        	
		        
			        Row row_ED = sheet_ED.createRow(sheet_ED.getLastRowNum()+1);
			        if(sheet_ED.getLastRowNum()==0){
	
			        }
	
			        Cell date = row_ED.createCell(0);
			        date.setCellValue(Date);
	
			        Cell Name1 = row_ED.createCell(1);
			        Name1.setCellValue(Name);
	
			        Cell ID = row_ED.createCell(2);
			        ID.setCellValue(id);
	
			        Cell Code = row_ED.createCell(3);
			        Code.setCellValue(charge);
	
			        Cell Task = row_ED.createCell(4);
			        Task.setCellValue(task[i]);
	
			        Cell Update = row_ED.createCell(5);
			        Update.setCellValue(update[i]);
			        
			        Cell Hour = row_ED.createCell(6);
			        Hour.setCellValue(hour[i]);


		            
		        }   

		        	FileOutputStream os_ED = new FileOutputStream(myFile_ED);
		        	workbook_ED.write(os_ED);
		            os_ED.close();
		            workbook_ED.close();
		            inputStream_ED.close();*/
		 		
			 }

	
	

}
