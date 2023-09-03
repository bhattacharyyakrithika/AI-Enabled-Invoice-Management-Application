package com.HRC.training;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DataLoad
 */
@WebServlet("/DataLoad")
public class DataLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HashMap<Object, Object> Response = new HashMap<Object, Object>();
		ArrayList<DataLoadPojoClass> ArrList = new ArrayList<DataLoadPojoClass>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose", "root", "root");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM winter_internship");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				DataLoadPojoClass pc = new DataLoadPojoClass(rs.getInt("sl_no"), rs.getString("business_code"), rs.getInt("cust_number"), rs.getString("clear_date"), rs.getString("buisness_year"), rs.getString("doc_id"), rs.getString("posting_date"), rs.getString("document_create_date"), rs.getString("due_in_date"), rs.getString("invoice_currency"), rs.getString("document_type"), rs.getInt("posting_id"), rs.getDouble("total_open_amount"), rs.getString("baseline_create_date"), rs.getString("cust_payment_terms"), rs.getInt("invoice_id"));
				ArrList.add(pc);
				
			}
			Response.put("winter_internship", ArrList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(Response);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.getWriter().append(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
