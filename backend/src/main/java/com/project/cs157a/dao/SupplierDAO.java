package com.project.cs157a.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SupplierDAO {

    public void addSupplier(String name, String contactInfo) throws SQLException {
        String sql = "INSERT INTO Suppliers(SupplierName, ContactInfo) VALUES(?, ?)";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, contactInfo);
        ps.executeUpdate();

        con.close();
        
    }

    public List<String> viewSuppliers() throws SQLException {
        List<String> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM Suppliers";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        //res contains the set of the entries, use res.getInt or res.getString
        while (res.next()) {
            suppliers.add(res.getInt("SupplierID") + " - " + res.getString("SupplierName"));
        }

        con.close();
        return suppliers;
    }

    public void updateSupplier(int supplierId, String contactInfo) throws SQLException {
        String sql = "UPDATE Suppliers SET ContactInfo = ? WHERE SupplierID = ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, contactInfo);
        ps.setInt(2, supplierId);
        ps.executeUpdate();

        con.close();
    }

    public void deleteSupplier(int supplierId) throws SQLException {
        String sql = "DELETE FROM Suppliers WHERE SupplierID = ?";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, supplierId);
        ps.executeUpdate();

        con.close();
    }
}
