# inventory-system
## Installation Instructions
Required:  
- Java 17  
- Maven  
- Docker  
- MySQL Image for Docker
```bash
    docker pull mysql:latest
```
1. Clone GitHub Repo
```bash
    git clone https://github.com/brian-p-tran/inventory-system.git
```
2. Running MySQL Docker Container
   1. Navigate to inventory-system\docker in terminal
   2. Run Docker Compose command
```bash
    docker-compose up -d
```
3. Running Springboot Java using Maven.
   1. Navigate to inventory-system\backend
   2. Run Maven Clean Install
```bash
    mvn clean install
```
   3. Run Springboot Tomcat Server
```bash
    mvn spring-boot:run
```
   4. Head over to the link below to test if everything is running
```bash
    http://localhost:8080/products
```

## Testing Servlets
We will be using Postman. We can use the Web App or the Desktop App.
You will need to sign up for an account.
```bash
    https://www.postman.com/
```
If using the web app above, make sure to install the Postman Desktop Agent and allow your web browser access.  

To use Postman  
1. Sign up an account
2. Create a blank workspace
3. Create a new collection (Select http)
4. Add two requests (1 GET, 1 POST)
5. Enter/Select one of the Servlet URLs listed
- http://localhost:8080/products
- http://localhost:8080/customers
- http://localhost:8080/sales
- http://localhost:8080/suppliers
6. GET testing will just be entering the link.
### POST
Each of the tables you can test in Postman.
Tables:  
**PRODUCTS**  
Add a Product (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| action  | add  |
| name  | Table  |
| category  | Home  |
| supplierId  | 12  |
| quantity  | 2  |
| reorder  | 1  |
| price  | 299.99  |  

Update Stock (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| action  | updateStock  |
| productId  | 1  |
| quantity  | 5  | 

Update Price (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| action  | updatePrice  |
| productId  | 1  |
| quantity  | 99.99  | 

Delete Product (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| action  | delete  |
| productId  | 1  |  

**CUSTOMERS**  
Add Customer (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| name  | Lebron James  |
| email  | lebronjames@example.com  | 
| quantity  | 982-1133  | 

**SALES**  
Add Sale (keys are case-sensitive)
Example:
| Key  | Value |
| ------------- | ------------- |
| productId  | 1  |
| name  | Lebron James  |
| customerId  | 3  | 
| qtySold  | 4  | 
| total  | 1499.99  | 

**SUPPLIERS**  
Add Supplier (keys are case-sensitive)
| Key  | Value |
| ------------- | ------------- |
| action  | add  |
| name  | New Supplier  |
| contactInfo  | new@supplier.com  |  

Update Supplier (keys are case-sensitive)
| Key  | Value |
| ------------- | ------------- |
| action  | update  |
| suplierId  | 2  |
| contactInfo  | updated@supplier.com  |  

Update Supplier (keys are case-sensitive)
| Key  | Value |
| ------------- | ------------- |
| action  | delete  |
| suplierId  | 2  |  

More features could be added.
