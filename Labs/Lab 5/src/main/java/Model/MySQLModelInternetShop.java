package Model;

import Model.Actors.Role;
import Model.Actors.User;
import Model.Data.DBConnectionData;
import Model.Data.Entity.Order;
import Model.Data.Entity.OrderStatus;
import Model.Data.Entity.Product;
import Model.Data.Entity.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLModelInternetShop extends ModelInternetShop {

    private Connection connection;
    private  Statement statement;

    public MySQLModelInternetShop() {
        DBConnectionData connectionData = new DBConnectionData();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    connectionData.getConnectionURL(),
                    connectionData.getLogin(),
                    connectionData.getPassword());

            this.statement = this.connection.createStatement();

            this.mainStorage = new Storage(this.readProducts());
            this.orders = this.readOrders();

            this.orders.forEach(this::reserveProducts);


            System.out.println("Connection to Store DB succesfull!");

        } catch (SQLException sqlEx){
            System.err.println("ERROR::SQLEXCEPTION was catched!");
            sqlEx.printStackTrace();
        }
        catch (ClassNotFoundException  classEx) {
            System.err.println("ERROR::ClassNotFoundException was catched!");
            classEx.printStackTrace();
        }
    }

    @Override
    public HashMap<Product, Integer> readProducts() {

        HashMap<Product, Integer> productsData = new HashMap<>();

        try {
            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
               String name = resultSet.getString("name_product");
               String manufacturer = resultSet.getString("manufacturer");
               float price = resultSet.getFloat("price");
               int count = resultSet.getInt("count_product");

               productsData.put(new Product(name, manufacturer, price), count);
            }

        } catch (SQLException sqlException) {
            System.out.println("Error read data from database!");
            System.out.println(sqlException.getSQLState());
            sqlException.printStackTrace();
        } finally {
            return productsData;
        }
    }

    @Override
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();

                long id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("user_password");
                String role = resultSet.getString("user_role");

                if(user.createUses(id, login, password, Role.valueOf(role))) {
                    users.add(user);
                }
            }

        } catch (SQLException sqlException) {
            System.out.println("Error read data from database!");
            System.out.println(sqlException.getSQLState());
            sqlException.printStackTrace();
        } finally {
            return users;
        }
    }

    @Override
    public ArrayList<Order> readOrders() {
        ArrayList<Order> ordersData = new ArrayList<>();

        try {
            String query = "SELECT id, customer_id, completed FROM orders";
            ResultSet ordersSet = this.statement.executeQuery(query);

            while (ordersSet.next()) {
                int id = ordersSet.getInt(1);
                int customer_id = ordersSet.getInt(2);
                boolean completed = ordersSet.getBoolean(3);

                Order order = new Order(customer_id);
                order.setId(id);

                PreparedStatement selectProducts = connection.prepareStatement(
                        "SELECT P.name_product, P.manufacturer, P.price, OP.count_product " +
                                "from products as P " +
                                "JOIN orders_products as OP on P.id = OP.product_id " +
                                "JOIN orders as O on OP.order_id = O.id " +
                                "WHERE O.id = ?"
                );
                selectProducts.setInt(1, id);

                ResultSet productsSet = selectProducts.executeQuery();

                while(productsSet.next()){
                    String name = productsSet.getString(1);
                    String manufacturer = productsSet.getString(2);
                    float price = productsSet.getFloat(3);
                    int count = productsSet.getInt(4);

                    order.addProduct(new Product(name, manufacturer, price), count);
                }
                if(completed){
                    order.setStatus(OrderStatus.COMPLETED);
                }
                order.setStatus(OrderStatus.NOTCOMPLETED);
                order.setId(id);
                ordersData.add(order);
            }
        } catch (SQLException sqlException) {
            System.out.println("Error read data from database!");
            System.out.println(sqlException.getSQLState());
            sqlException.printStackTrace();
        } finally {
            return ordersData;
        }
    }

    @Override
    public boolean addOrder(Order order) {
        if(super.addOrder(order)) {
            try{
                ResultSet maxId = statement.executeQuery("select max(id) from orders");

                while (maxId.next()){
                    int idOrder = maxId.getInt(1);
                    order.setId(idOrder);
                }

                PreparedStatement addingStatement = this.connection.prepareStatement(
                        "INSERT INTO orders(customer_id, total_price, completed) VALUES(?,?,?)"
                );

                addingStatement.setLong(1, order.getCustomerId());
                addingStatement.setFloat(2, order.calculatePrice());
                addingStatement.setBoolean(3, false);

                addingStatement.executeUpdate();

                for (Product product : order.getProducts().keySet()) {
                    PreparedStatement selectStatement = this.connection.prepareStatement(
                            "SELECT id FROM products WHERE name_product = ? and manufacturer = ?"
                    );
                    selectStatement.setString(1, product.getName());
                    selectStatement.setString(2, product.getManufacturer());

                    ResultSet productSet = selectStatement.executeQuery();
                    while (productSet.next()){
                        long id = productSet.getInt("id");
                        addingStatement = this.connection.prepareStatement(
                                "INSERT INTO orders_products(order_id, product_id, count_product) VALUES (?,?,?)"
                        );
                        addingStatement.setLong(1, order.getId());
                        addingStatement.setLong(2, id);
                        addingStatement.setInt(3, order.getProducts().get(product));
                        addingStatement.executeUpdate();
                    }
                }

                return true;

            } catch (SQLException sqlException){
                System.out.println("Error adding order to database!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean removeOrder(Long id) {
        if(super.removeOrder(id)){
            try {
                PreparedStatement removeStatement = this.connection.prepareStatement(
                    "DELETE FROM orders WHERE id = ?"
                );
                removeStatement.setLong(1, id);
                removeStatement.executeUpdate();

                return true;

            } catch (SQLException sqlException){
                System.out.println("Error removing order!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean addProductToOrder(Order order, Product product, int count) {
        if(super.addProductToOrder(order, product, count)) {
            try{
                PreparedStatement addingProductStatement = this.connection.prepareStatement(
                        "SELECT id FROM products WHERE name_product = ? and manufacturer = ?"
                );
                addingProductStatement.setString(1, product.getName());
                addingProductStatement.setString(2, product.getManufacturer());
                ResultSet productSet = addingProductStatement.executeQuery();

                while (productSet.next()) {
                    addingProductStatement = this.connection.prepareStatement(
                      "INSERT INTO orders_products(order_id, product_id, count_product) VALUES (?,?,?)"
                    );
                    addingProductStatement.setLong(1, order.getId());
                    addingProductStatement.setLong(2, productSet.getLong("id"));
                    addingProductStatement.setInt(3, count);
                    addingProductStatement.executeUpdate();
                }
                this.updateOrderPrice(order);
                return true;

            } catch (SQLException sqlException){
                System.out.println("Error adding product to order!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean removeProductFormOrder(Order order, Product product) {
        if(super.removeProductFormOrder(order, product)){
            try{
                PreparedStatement removeProductStatement = this.connection.prepareStatement(
                        "SELECT id FROM products WHERE name_product = ? and manufacturer = ?"
                );
                removeProductStatement.setString(1, product.getName());
                removeProductStatement.setString(2, product.getManufacturer());
                ResultSet productSet = removeProductStatement.executeQuery();

                while (productSet.next()){
                    removeProductStatement = this.connection.prepareStatement(
                      "DELETE FROM orders_products WHERE product_id = ? and order_id = ?"
                    );
                    removeProductStatement.setLong(1, productSet.getLong("id"));
                    removeProductStatement.setLong(2, order.getId());
                    removeProductStatement.executeUpdate();
                }
                this.updateOrderPrice(order);
                return true;

            } catch (SQLException sqlException){
                System.out.println("Error remove product to order!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean completeOrder(Long id) {
        if(super.completeOrder(id)) {
            try{
                //update table orders
                PreparedStatement completingStatement = this.connection.prepareStatement(
                    "UPDATE orders SET completed = 1 WHERE id = ?"
                );
                completingStatement.setLong(1, id);
                completingStatement.executeUpdate();

                //get products id from orders
                completingStatement = this.connection.prepareStatement(
                        "SELECT product_id, count_product FROM orders_products WHERE order_id = ?"
                );
                completingStatement.setLong(1, id);
                ResultSet productsIdSet = completingStatement.executeQuery();

                //update table products
                while (productsIdSet.next()){
                    PreparedStatement productsStatement = this.connection.prepareStatement(
                        "UPDATE products SET count_product = count_product - ? WHERE id = ?"
                    );
                    productsStatement.setInt(1, productsIdSet.getInt("count_product"));
                    productsStatement.setInt(2, productsIdSet.getInt("product_id"));
                    System.out.println(productsIdSet.getInt("product_id"));

                    productsStatement.executeUpdate();
                }

                return true;
            } catch (SQLException sqlException){
                System.out.println("Error completing order!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean writeProducts(Storage storage) {
        if(storage.collapse(this.reservedStorage)) {
            try{
                for (Product product : storage.getProducts()) {
                    PreparedStatement updateStatement = this.connection.prepareStatement(
                            "UPDATE products SET count_product = ? WHERE name_product = ? and manufacturer = ?"
                    );
                    updateStatement.setInt(1, storage.getStorageData().get(product));
                    updateStatement.setString(2, product.getName());
                    updateStatement.setString(3, product.getManufacturer());
                    updateStatement.executeUpdate();
                }
                return true;
            } catch (SQLException sqlException){
                System.out.println("Error remove product to order!");
                System.out.println(sqlException.getSQLState());
                sqlException.printStackTrace();
            }
        }
       return false;
    }

    private void updateOrderPrice(Order order){
        try{
            PreparedStatement updatingPrice = this.connection.prepareStatement(
                    "UPDATE orders SET total_price = ? WHERE id = ?"
            );
            updatingPrice.setFloat(1, order.calculatePrice());
            updatingPrice.setLong(2, order.getId());
            updatingPrice.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getSQLState());
            sqlException.printStackTrace();
        }
    }

}
