package com.project.cs157a.servlet;

import com.project.cs157a.dao.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {

    private SupplierDAO supplierDAO;

    @Override
    public void init() throws ServletException {
        supplierDAO = new SupplierDAO();
    }

    // view suppliers
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Suppliers</h2>");

        try {
            List<String> suppliers = supplierDAO.viewSuppliers();
            for (String s : suppliers) {
                out.println("<p>" + s + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        out.println("<hr>");
        out.println("<h3>Add Supplier</h3>");
        out.println("""
            <form method="post" action="/suppliers">
                Name: <input name="name"><br>
                Contact Info: <input name="contactInfo"><br>
                <input type="hidden" name="action" value="add">
                <button type="submit">Add</button>
            </form>
        """);
    }

    //add, update, delete
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                supplierDAO.addSupplier(
                    request.getParameter("name"),
                    request.getParameter("contactInfo")
                );

            } else if ("update".equals(action)) {
                supplierDAO.updateSupplier(
                    Integer.parseInt(request.getParameter("supplierId")),
                    request.getParameter("contactInfo")
                );

            } else if ("delete".equals(action)) {
                supplierDAO.deleteSupplier(
                    Integer.parseInt(request.getParameter("supplierId"))
                );
            }

            response.sendRedirect("/suppliers");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
