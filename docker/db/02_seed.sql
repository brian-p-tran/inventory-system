USE my_database;

-- customers
INSERT INTO Customers (CustomerName, Email, Phone) VALUES
('Alice Johnson', 'alice@example.com', '555-1001'),
('Bob Smith', 'bob@example.com', '555-1002'),
('Carol White', 'carol@example.com', '555-1003'),
('David Brown', 'david@example.com', '555-1004'),
('Eve Black', 'eve@example.com', '555-1005'),
('Frank Moore', 'frank@example.com', '555-1006'),
('Grace Clark', 'grace@example.com', '555-1007'),
('Henry Lee', 'henry@example.com', '555-1008'),
('Ivy Scott', 'ivy@example.com', '555-1009'),
('Jack Young', 'jack@example.com', '555-1010'),
('Karen Hall', 'karen@example.com', '555-1011'),
('Leo Adams', 'leo@example.com', '555-1012'),
('Mona Turner', 'mona@example.com', '555-1013'),
('Nick Perez', 'nick@example.com', '555-1014'),
('Olivia Reed', 'olivia@example.com', '555-1015');

-- suppliers
INSERT INTO Suppliers (SupplierName, ContactInfo) VALUES
('Global Tech Supply', 'contact@globaltech.com'),
('OfficeSmart', 'sales@officesmart.com'),
('QuickParts Inc.', 'support@quickparts.com'),
('WarehousePro', 'info@warehousepro.com'),
('RetailSource', 'orders@retailsource.com'),
('Best Supplies', 'hello@bestsupplies.com'),
('SupplyHub', 'service@supplyhub.com'),
('MegaGoods', 'contact@megagoods.com'),
('DirectStock', 'stock@directstock.com'),
('Central Distributors', 'central@dist.com'),
('Northline Supply', 'north@supply.com'),
('Eastline Supply', 'east@supply.com'),
('Southline Supply', 'south@supply.com'),
('Westline Supply', 'west@supply.com'),
('Universal Supplies', 'universal@supply.com');

-- products
INSERT INTO Products
(Name, Category, SupplierID, QuantityInStock, ReorderLevel, UnitPrice) VALUES
('Laptop', 'Electronics', 1, 50, 10, 899.99),
('Keyboard', 'Electronics', 1, 150, 30, 29.99),
('Mouse', 'Electronics', 2, 200, 40, 19.99),
('Monitor', 'Electronics', 3, 80, 15, 199.99),
('Printer', 'Office', 4, 40, 10, 149.99),
('Desk Chair', 'Furniture', 5, 60, 10, 129.99),
('Desk Lamp', 'Office', 6, 120, 20, 24.99),
('Notebook', 'Stationery', 7, 500, 100, 2.99),
('Pen Pack', 'Stationery', 7, 600, 150, 5.99),
('File Organizer', 'Office', 8, 100, 20, 14.99),
('USB Drive', 'Electronics', 9, 300, 50, 15.99),
('External HDD', 'Electronics', 10, 70, 15, 89.99),
('Webcam', 'Electronics', 11, 90, 20, 49.99),
('Headset', 'Electronics', 12, 110, 25, 59.99),
('Router', 'Networking', 13, 65, 15, 79.99);

-- Sales
INSERT INTO Sales
(ProductID, CustomerID, SaleDate, QuantitySold, TotalAmount) VALUES
(1, 1, NOW(), 1, 899.99),
(2, 2, NOW(), 2, 59.98),
(3, 3, NOW(), 3, 59.97),
(4, 4, NOW(), 1, 199.99),
(5, 5, NOW(), 1, 149.99),
(6, 6, NOW(), 2, 259.98),
(7, 7, NOW(), 3, 74.97),
(8, 8, NOW(), 10, 29.90),
(9, 9, NOW(), 5, 29.95),
(10, 10, NOW(), 2, 29.98),
(11, 11, NOW(), 4, 63.96),
(12, 12, NOW(), 1, 89.99),
(13, 13, NOW(), 2, 99.98),
(14, 14, NOW(), 1, 59.99),
(15, 15, NOW(), 1, 79.99);
