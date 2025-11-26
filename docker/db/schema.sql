CREATE TABLE Suppliers(
    SupplierID INT AUTO_INCREMENT PRIMARY KEY,
    SupplierName VARCHAR(255) NOT NULL,
    ContactInfo VARCHAR(255)
);

CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    SupplierID INT NOT NULL,
    QuantityInStock INT NOT NULL DEFAULT 0,
    ReorderLevel INT NOT NULL DEFAULT 0,
    UnitPrice DECIMAL(10,2) NOT NULL,
    
    CONSTRAINT fk_products_supplier
        FOREIGN KEY (SupplierID)
        REFERENCES Suppliers(SupplierID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE Sales(
    SaleID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID INT NOT NULL,
    SaleDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    QuantiySold INT NOT NULL,
    TotalAmount DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_sales_product
        FOREIGN KEY (ProductID)
        REFERENCES Products(ProductID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT fk_sales_customer
        FOREIGN KEY (CustomerID)
        REFERENCES Customers(CustomerID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE,
    Phone VARCHAR(50),
);