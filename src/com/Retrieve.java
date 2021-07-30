package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Step-1: Database Connection 
	private static final String url = "jdbc:mysql://localhost:3306/PRODUCT";
	private static final String user = "root"; 
	private static final String password = "root";
    
	//Step-2: JDBC variables for opening and managing connection 
		private static Connection con = null;
		private static Statement stmt = null;
		private static ResultSet rs;
		
		
    public Retrieve() {
        super();
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String id=request.getParameter("id");
		out.print("<h1>Display The Record: </h1>");
		out.print("<table border='1'><tr><th>Id</th><th>Name</th><th>Price</th></tr>");
		
		try {
		//Step-3: Register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		//Step-4: Opening database Connection To MySQL SERVER
			con = DriverManager.getConnection(url, user, password);
			
	    //Step-5: Getting Statement Object to execute Query
			stmt = con.createStatement();
			
			String query1 = "SELECT * FROM product_table WHERE id = " + id; 
			
		//Step-6: Executing SELECT Query
			rs = stmt.executeQuery(query1);
			
			if(rs.next()) {
				System.out.println("Retrieve Product Details Successfully!!!!!!!!!");
				out.print("<tr><td>");
		    	out.println(rs.getInt(1));
		    	out.print("</td>");
		    	out.print("<td>");
		    	out.println(rs.getString(2));
		    	out.print("</td>");
		    	out.print("<td>");
		    	out.println(rs.getBigDecimal(3));
		    	out.print("</td>");
		    	out.print("</tr>");
			}else {
				System.out.println("Sorry,Product Id Not Found. Please Enter Again.... ");
			}
			
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				if(con !=null)
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			try {
				if(con !=null)
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			out.print("</table>");		
		}						
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
