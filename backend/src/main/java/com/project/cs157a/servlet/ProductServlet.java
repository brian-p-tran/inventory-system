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
    private static final String ADMIN_KEY = "admin123";

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        try {
            // 1. Low stock
            if ("lowStock".equals(action)) {
                out.println("<h2>Low Stock Products</h2>");
                List<String> products = productDAO.viewLowStockProducts();

                if (products.isEmpty()) {
                    out.println("<p>No low stock items found</p>");
                } else {
                    for (String p : products) {
                        out.println("<p>" + p + "</p>");
                    }
                }
                return;
            }

            // 2. Search
            if ("search".equals(action)) {
                String keyword = request.getParameter("q");

                if (keyword == null || keyword.isBlank()) {
                    out.println("<p>Please provide search keyword using ?q=</p>");
                    return;
                }

                out.println("<h2>Search Results for '" + keyword + "'</h2>");

                List<String> products = productDAO.searchProducts(keyword);

                if (products.isEmpty()) {
                    out.println("<p>No matching products found</p>");
                } else {
                    for (String p : products) {
                        out.println("<p>" + p + "</p>");
                    }
                }
                return;
            }

            // default: view all
            out.println("<h2>Product List</h2>");
            List<String> products = productDAO.viewProducts();
            for (String p : products) {
                out.println("<p>" + p + "</p>");
            }

        } catch (SQLException e) {
            out.println("<p>Error loading products</p>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // authorization check for ANY write action
        String adminKey = request.getParameter("adminKey");
        if (adminKey == null || !ADMIN_KEY.equals(adminKey)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not authorized to modify inventory");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addProduct(request);
            } else if ("updateStock".equals(action)) {
                updateStock(request);
            } else if ("updatePrice".equals(action)) {
                updatePrice(request);
            } else if ("delete".equals(action)) {
                deleteProduct(request);
            }

            response.sendRedirect("products");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

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
