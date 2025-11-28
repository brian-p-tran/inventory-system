package com.project.cs157a.dao;
import java.sql.*;


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

    public void viewSuppliers() throws SQLException {
        String sql = "SELECT * FROM Suppliers";

        Connection con = DBCon.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        //res contains the set of the entries, use res.getInt or res.getString

        con.close();
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
