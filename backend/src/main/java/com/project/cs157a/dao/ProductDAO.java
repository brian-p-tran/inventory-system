package com.project.cs157a.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void addProduct(String name, String category, int supplierId, int quantity, int reorder, double price) throws SQLException {
        String sql = "INSERT INTO Products (Name, Category, SupplierID, QuantityInStock, ReorderLevel, UnitPrice) " +
        "VALUES (?, ?, ?, ?, ? ,?)";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, category);
        ps.setInt(3, supplierId);
        ps.setInt(4, quantity);
        ps.setInt(5, reorder);
        ps.setDouble(6, price);
        ps.executeUpdate();

        con.close();
    }
    public List<String> viewProducts() throws SQLException {
        List<String> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        //res contains the set of the entries, use res.getInt or res.getString
        while (res.next()) {
            products.add(res.getInt("ProductID") + " | " + res.getString("Name") + " | " + res.getString("Category") + " | $" + res.getDouble("UnitPrice") + " | Qty: " + res.getInt("QuantityInStock"));
        }

        con.close();
        return products;
    }
    public void updateStock(int productId, int newQty) throws SQLException {
        String sql =
            "UPDATE Products SET QuantityInStock = ? WHERE ProductID = ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, newQty);
        ps.setInt(2, productId);
        ps.executeUpdate();

        con.close();
    }
    public void updatePrice(int productId, double newPrice) throws SQLException {
        String sql =
            "UPDATE Products SET UnitPrice = ? WHERE ProductID = ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, newPrice);
        ps.setInt(2, productId);
        ps.executeUpdate();

        con.close();
    }

    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ps.executeUpdate();

        con.close();
    }
    
    // get products where quantity is at or below reorder level
    public List<String> viewLowStockProducts() throws SQLException {
        List<String> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE QuantityInStock <= ReorderLevel";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            products.add(
                res.getInt("ProductID") + " | " +
                res.getString("Name") + " | " +
                res.getString("Category") + " | $" +
                res.getDouble("UnitPrice") + " | Qty: " +
                res.getInt("QuantityInStock")
            );
        }

        con.close();
        return products;
    }

    // search products by name or category
    public List<String> searchProducts(String keyword) throws SQLException {
        List<String> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Name LIKE ? OR Category LIKE ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        String pattern = "%" + keyword + "%";
        ps.setString(1, pattern);
        ps.setString(2, pattern);

        ResultSet res = ps.executeQuery();

        while (res.next()) {
            products.add(
                res.getInt("ProductID") + " | " +
                res.getString("Name") + " | " +
                res.getString("Category") + " | $" +
                res.getDouble("UnitPrice") + " | Qty: " +
                res.getInt("QuantityInStock")
            );
        }

        con.close();
        return products;
    }

}
