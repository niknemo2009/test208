package Model.Data;

import Model.Actors.User;
import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.Data.Entity.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public interface DataBase {
    HashMap<Product, Integer> readProducts();

    ArrayList<User> readUsers();

    ArrayList<Order> readOrders();

    boolean writeProducts(Storage storage);
}
