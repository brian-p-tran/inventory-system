package com.project.cs157a.servlet;

import com.project.cs157a.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    //View Products
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Product List</h2>");

        try {
            List<String> products = productDAO.viewProducts();
            for (String p : products) {
                out.println("<p>" + p + "</p>");
            }
        } catch (SQLException e) {
            out.println("<p>Error loading products</p>");
            e.printStackTrace();
        }
    }

    // add, update, delete
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addProduct(request);
            } 
            else if ("updateStock".equals(action)) {
                updateStock(request);
            } 
            else if ("updatePrice".equals(action)) {
                updatePrice(request);
            } 
            else if ("delete".equals(action)) {
                deleteProduct(request);
            }

            response.sendRedirect("products");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    //helper methods
    private void addProduct(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int reorder = Integer.parseInt(request.getParameter("reorder"));
        double price = Double.parseDouble(request.getParameter("price"));

        productDAO.addProduct(name, category, supplierId, quantity, reorder, price);
    }

    private void updateStock(HttpServletRequest request) throws SQLException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int newQty = Integer.parseInt(request.getParameter("quantity"));

        productDAO.updateStock(productId, newQty);
    }

    private void updatePrice(HttpServletRequest request) throws SQLException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        double price = Double.parseDouble(request.getParameter("price"));

        productDAO.updatePrice(productId, price);
    }

    private void deleteProduct(HttpServletRequest request) throws SQLException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productDAO.deleteProduct(productId);
    }
}

