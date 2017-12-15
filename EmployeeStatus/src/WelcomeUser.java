import java.io.*; 

import javax.servlet.*; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet(value = "/welcome") 
public class WelcomeUser extends HttpServlet { 
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "C://Users//GUR47765//Pictures//EmployeeDetail//TestData.xlsx";

public void doPost(HttpServletRequest request, 
 HttpServletResponse response) 
 throws ServletException, IOException 
 { 
 
    response.setContentType("text/html"); 
    PrintWriter out = response.getWriter(); 
 
    
    Date myDate = new Date();
    
    String formatted =new SimpleDateFormat("yyyy-MM-dd").format(myDate);
    
    HttpSession session=request.getSession();
  

    String name=request.getParameter("uname"); 
    String id=request.getParameter("uid");  
    
    session.setAttribute("uname",name);
    session.setAttribute("uid",id);
    
	Cookie loginCookie = new Cookie("user",id);

	loginCookie.setMaxAge(30*60);
	response.addCookie(loginCookie);
       
    Date today = new java.util.Date();
    FileOutputStream fos = null;
    File file;
    String mycontent ="Date : "+today+ "\nName : " +name+"\nEmployee ID : "+id+"\n";
    
  /*  try {
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
    	   } 
    	   catch (IOException ioe) {
    		   System.out.println("Error in closing the Stream");
    	   }
       }*/
 
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<style>  table, th, td {border: 1px solid black;} #test {color: OrangeRed;} p{align=center;color: blue;font-size: 150%;} </style>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Aricent</title></head>");
        out.println("<body background=\"C:\\Users\\GUR47765\\Pictures\\EmployeeDetail\\col.jpg\"  >");
        out.println("<div align=\"right\"><form action=\"index.html\" method=\"post\"><input type=\"submit\" value=\"Logout\"></form></div>");
        out.println("<marquee><p>Login is successful</h1></p></marquee>");  
        out.println("<center><p>TIME SHEET UPDATE:</p></center>");
      //  out.println("<p>Time: " + today + "</p>");
      //  out.println("<p>Name: " + name + "</p>");
      //  out.println("<p>Employee ID: " + id + "</p>");
        
        out.println("<center><form id=\"test\" action=\"update\" method=\"post\">"
        			+"Date:<input type=\"date\" name=\"date\" value="+formatted+">");
        /* out.println("User Name: <select name=\"uname\"  id=\"mySel\" onchange=\"myFunc()\">");
        out.println("<option value="+name+">"+name+"");
        out.println("<option value=\"Basavaraj\">Basavaraj");
        out.println("<option value=\"Vishal\">Vishal");
        out.println("<option value=\"Vikram\">Vikram");
        out.println("<option value=\"Vijay\">Vijay");
        out.println("<option value=\"Vinith\">Vinith");
        out.println("</select><br><br>"); */
        
        out.println("Name:<input readonly=\"readonly\" id=\"Myid\" type=\"text\" name=\"uname\" value="+session.getAttribute("uname")+"><br><br>");
    	out.println("Employee ID:<input readonly=\"readonly\" id=\"Myid\" type=\"text\" name=\"uid\" value="+session.getAttribute("uid")+">"
        			+"&nbsp Charge code:<input type=\"text\" name=\"charge\" required=\"true\"/><br/><br>"
    				+"<table style=\"width:75%\"><tr><th>TASK</th><th>STATUS</th> <th>HOUR</th></tr>"
        			+"<tr><td><textarea rows=\"1\" cols=\"70\"  name=\"task1\"></textarea></td> <td><textarea  rows=\"1\" cols=\"70\" name=\"update1\"></textarea></td><td><input type=\"number\" name=\"hour1\" /></td></tr>"
        			+"<tr> <td><textarea rows=\"1\" cols=\"70\"  name=\"task2\"></textarea></td><td><textarea  rows=\"1\" cols=\"70\" name=\"update2\"></textarea></td><td><input type=\"number\" name=\"hour2\" /></td></tr>"
        			+"<tr><td><textarea rows=\"1\" cols=\"70\"  name=\"task3\"></textarea></td><td><textarea  rows=\"1\" cols=\"70\" name=\"update3\"></textarea></td><td><input type=\"number\" name=\"hour3\" /></td> </tr>"
        			+"<tr><td><textarea rows=\"1\" cols=\"70\"  name=\"task4\"></textarea></td><td><textarea  rows=\"1\" cols=\"70\" name=\"update4\"></textarea></td><td><input type=\"number\" name=\"hour4\" /></td> </tr>"
        			+"<tr><td><textarea rows=\"1\" cols=\"70\"  name=\"task5\"></textarea></td><td><textarea  rows=\"1\" cols=\"70\" name=\"update5\"></textarea></td><td><input type=\"number\" name=\"hour5\" /></td> </tr>"
        			+"</table><br><br>"
        			+"<input type=\"submit\" value=\"Submit\"></form></center>");
    	
    	out.println("<script>");
    	out.println("function myFunc() {");
    	out.println("var x = document.getElementById(\"mySel\").value;");
    	out.println("switch(x){");
    	out.println("case \"Basavaraj\":");
    	out.println("document.getElementById(\"Myid\").value = 100;"); 
    	out.println("break;");
    	out.println("case \"Vishal\":");
    	out.println("document.getElementById(\"Myid\").value = 101;"); 
    	out.println("break;");
    	out.println("case \"Vikram\":");
    	out.println("document.getElementById(\"Myid\").value = 102;"); 
    	out.println("break;");
    	out.println("case \"Vijay\":");
    	out.println("document.getElementById(\"Myid\").value = 103;"); 
    	out.println("break;");
    	out.println("case \"Vinith\":");
    	out.println("document.getElementById(\"Myid\").value = 104;"); 
    	out.println("break;");
    	out.println("default:");
    	out.println("document.getElementById(\"Myid\").value = 0;"); 
    	out.println("break;");
    	out.println("}");
    	out.println("}");
    	out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        
    /*    File myFile_ED = new File("C://Users//GUR47765//Pictures//EmployeeDetail//TestData.xlsx");
        FileInputStream inputStream_ED = new FileInputStream(myFile_ED);

        XSSFWorkbook workbook_ED = new XSSFWorkbook(inputStream_ED);
        XSSFSheet sheet_ED = workbook_ED.getSheetAt(0);
        Iterator<Row> riterator_ED = sheet_ED.iterator();
        Row row_ED = sheet_ED.createRow(sheet_ED.getLastRowNum()+1);
        if(sheet_ED.getLastRowNum()==0){

        }

        Cell date = row_ED.createCell(0);
        date.setCellValue(formatted);

        Cell Name = row_ED.createCell(1);
        Name.setCellValue(name);

        Cell ID = row_ED.createCell(2);
        ID.setCellValue(id);


        FileOutputStream os_ED = new FileOutputStream(myFile_ED);
            workbook_ED.write(os_ED);

            os_ED.close();
            workbook_ED.close();
            inputStream_ED.close();*/





    /*if (x == "Basavaraj")
    	{
    		
    	}*/
    

    





 } 
}

 