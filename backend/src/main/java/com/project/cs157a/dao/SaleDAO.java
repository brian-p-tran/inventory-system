package com.project.cs157a.dao;

import java.sql.*;

public class SaleDAO {
    public void addSale(int productId, int customerId, int qtySold, double total) throws SQLException {
        String sql = "INSERT INTO Sales (ProductID, CustomerID, QuantitySold, TotalAmount) " +
        "VALUES (?, ?, ?, ?)";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, productId);
        ps.setInt(2, customerId);
        ps.setInt(3, qtySold);
        ps.setDouble(4, total);
        ps.executeUpdate();

        con.close();
    }

    public void viewSales() throws SQLException {
        String sql =
        "SELECT s.SaleID, p.Name, c.CustomerName, s.QuantitySold, s.TotalAmount " +
        "FROM Sales s " +
        "JOIN Products p ON s.ProductID = p.ProductID " +
        "JOIN Customers c ON s.CustomerID = c.CustomerID";

        Connection con = DBCon.getConnection();
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery(sql);

        //res contains the set of the entries, use res.getInt or res.getString

        con.close();
    }
}
