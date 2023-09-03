package com.HRC.training;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class advanceSearchData
 */
@WebServlet("/advanceSearchData")
public class advanceSearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public advanceSearchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		HashMap<Object,Object> Response= new HashMap<Object,Object>();
		ArrayList<DataLoadPojoClass> list = new ArrayList<DataLoadPojoClass>();
		
		String doc_id = request.getParameter("doc_id");
		int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
		int cust_number = Integer.parseInt(request.getParameter("cust_number"));
		String buisness_year = request.getParameter("buisness_year");
		
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose", "root", "root");
			String query = "SELECT * FROM winter_internship where doc_id="+doc_id+" and invoice_id="+invoice_id+" and cust_number="+cust_number+" and buisness_year="+buisness_year;
			
			PreparedStatement pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				DataLoadPojoClass dt = new DataLoadPojoClass(rs.getInt("sl_no"), rs.getString("business_code"), rs.getInt("cust_number"), rs.getString("clear_date"), rs.getString("buisness_year"), rs.getString("doc_id"), rs.getString("posting_date"), rs.getString("document_create_date"), rs.getString("due_in_date"), rs.getString("invoice_currency"), rs.getString("document_type"), rs.getInt("posting_id"), rs.getDouble("total_open_amount"), rs.getString("baseline_create_date"), rs.getString("cust_payment_terms"), rs.getInt("invoice_id"));
				
				list.add(dt);
			}
			Response.put("advance", list);
			
			Gson gson = new Gson();
			String val = gson.toJson(Response);
	        response.setContentType("application/json");
			response.getWriter().append(val);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
