package com.project.cs157a.servlet;

import com.project.cs157a.dao.SaleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/sales")
public class SaleServlet extends HttpServlet {

	private SaleDAO saleDAO;
	private static final String ADMIN_KEY = "admin123";

	@Override
	public void init() throws ServletException {
		saleDAO = new SaleDAO();
	}

	// view sales
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<h2>Sales List</h2>");

		try {
			List<String> sales = saleDAO.viewSales();
			for (String s : sales) {
				out.println("<p>" + s + "</p>");
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	// add sale (protected by admin key)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// basic authorization: only admin can record sales that change inventory
		String adminKey = request.getParameter("adminKey");
		if (adminKey == null || !ADMIN_KEY.equals(adminKey)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not authorized to record sales");
			return;
		}

		int productId = Integer.parseInt(request.getParameter("productId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int qtySold = Integer.parseInt(request.getParameter("qtySold"));
		double total = Double.parseDouble(request.getParameter("total"));

		try {
			boolean success = saleDAO.addSale(productId, customerId, qtySold, total);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			if (success) {
				out.println("<h3>Sale completed successfully</h3>");
			} else {
				out.println("<h3 style='color:red'>❌ Insufficient inventory</h3>");
			}

			out.println("<a href='/sales'>Back to Sales</a>");

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
