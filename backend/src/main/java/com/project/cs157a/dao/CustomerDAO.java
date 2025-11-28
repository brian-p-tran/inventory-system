package com.project.cs157a.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public void addCustomer(String name, String email, String phone) throws SQLException {
        String sql = "INSERT INTO Customers (CustomerName, Email, Phone) VALUES (?, ?, ?)";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phone);
        ps.executeUpdate();

        con.close();
        
    }

    public void viewCustomers() throws SQLException {
        String sql = "SELECT * FROM Customers";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        //res contains the set of the entries, use res.getInt or res.getString

        con.close();
    }
}
