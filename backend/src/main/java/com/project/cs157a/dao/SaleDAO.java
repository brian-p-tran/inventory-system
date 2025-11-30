package com.project.cs157a.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

	// create a sale and decrease product stock in one transaction
	public boolean addSale(int productId, int customerId, int qtySold, double total) throws SQLException {
		String checkSQL = "SELECT QuantityInStock FROM Products WHERE ProductID = ?";
		String insertSQL = "INSERT INTO Sales (ProductID, CustomerID, QuantitySold, TotalAmount) "
				+ "VALUES (?, ?, ?, ?)";
		String updateSQL = "UPDATE Products SET QuantityInStock = QuantityInStock - ? WHERE ProductID = ?";

		Connection con = DBCon.getConnection();
		con.setAutoCommit(false); // start transaction

		try {
			// 1) check inventory level
			try (PreparedStatement check = con.prepareStatement(checkSQL)) {
				check.setInt(1, productId);
				ResultSet res = check.executeQuery();
				if (!res.next() || res.getInt("QuantityInStock") < qtySold) {
					con.rollback();
					con.close();
					return false;
				}
			}

			// 2) insert sale row
			try (PreparedStatement sale = con.prepareStatement(insertSQL)) {
				sale.setInt(1, productId);
				sale.setInt(2, customerId);
				sale.setInt(3, qtySold);
				sale.setDouble(4, total);
				sale.executeUpdate();
			}

			// 3) update product stock
			try (PreparedStatement update = con.prepareStatement(updateSQL)) {
				update.setInt(1, qtySold);
				update.setInt(2, productId);
				update.executeUpdate();
			}

			con.commit();
			con.close();
			return true;

		} catch (SQLException e) {
			con.rollback();
			con.close();
			throw e;
		}
	}

	public List<String> viewSales() throws SQLException {
		List<String> sales = new ArrayList<>();
		String sql = "SELECT s.SaleID, p.Name, c.CustomerName, s.QuantitySold, s.TotalAmount " + "FROM Sales s "
				+ "JOIN Products p ON s.ProductID = p.ProductID " + "JOIN Customers c ON s.CustomerID = c.CustomerID";

		Connection con = DBCon.getConnection();
		Statement statement = con.createStatement();
		ResultSet res = statement.executeQuery(sql);

		while (res.next()) {
			sales.add(res.getInt("SaleID") + " - " + res.getString("Name") + " - " + res.getString("CustomerName")
					+ " - " + res.getInt("QuantitySold") + " - $" + res.getDouble("TotalAmount"));
		}

		con.close();
		return sales;
	}
}
