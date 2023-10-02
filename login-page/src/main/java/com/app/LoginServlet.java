package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/test","root","root");
		    Statement st = conn.createStatement();
		    String query = "select * from user where userid = '"+userId+" and password = '"+password+"'";
		    ResultSet rs = st.executeQuery(query);
		    if(rs.next()) {
		    	out.print("<h1>" +userId+" Welcome to home page </h1><br>");
		    	out.print("<h1> Login Successfully!</h1><br>");
		    }else {
		    	out.print("<h1>" +userId+" please enter correct userId and Password </h1><br>");
		    	out.print("<h1> Login Failed!</h1><br>");
		    }
		    rs.close();
		    st.close();
		    conn.close();
		}catch (ClassNotFoundException e) {
			
	    	out.print("<h1> Login failed! because of server exception</h1><br>");
			e.printStackTrace();
		}catch(SQLException e) {
			out.print("<h1> Login failed! because of server exception</h1><br>");
			e.printStackTrace();
		}
		
		out.print("userId:"+userId);
		out.print("Password"+password);
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
