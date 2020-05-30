package Model.Data;

import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.FileModelInternetShop;

import java.util.ArrayList;

public interface Model extends DataBase {
    ArrayList<Order> getOrders();
    ArrayList<Order> getOrders(Long customerId);
    ArrayList<Long> getOrdersId();
    ArrayList<Long> getOrdersId(Long customerId);
    boolean removeOrder(Long id);
    Order getOrder(Long id);


    boolean addProductToOrder(Order order, Product product, int count);
    boolean removeProductFormOrder(Order order, Product product);

    ArrayList<String> getProductsList();
    ArrayList<String> getManufacturers(String productName);
    ArrayList<Product> getAllProducts();
    ArrayList<Product> findByName(String name);
    ArrayList<Product> findByManufacturer(String manufacturer);
    Product getProduct(String name, String manufacturer);

    void finalization();

    boolean checkProduct(Product product, int count);
    boolean addOrder(Order order);
    boolean completeOrder(Long id);

    static Model getModel(TypeModel type) {
        if (type == TypeModel.FILE) {
            return new FileModelInternetShop();
        }

        return new FileModelInternetShop();
    }

}
