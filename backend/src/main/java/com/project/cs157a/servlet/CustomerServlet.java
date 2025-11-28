package com.project.cs157a.servlet;

import com.project.cs157a.dao.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAO();
    }

    //view customers
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Customer List</h2>");

        try {
            List<String> customers = customerDAO.viewCustomers();
            for (String c : customers) {
                out.println("<p>" + c + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    //add customer
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            customerDAO.addCustomer(name, email, phone);
            response.sendRedirect("/customers");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
