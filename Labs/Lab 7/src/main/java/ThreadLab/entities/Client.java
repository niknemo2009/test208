package ThreadLab.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
    private String username;
    private Map<Product, Integer> products;
    private float cash;
    private Invoice currentInvoice;

    public Client(String username, float cash) {
        this.username = username;
        this.products = new ConcurrentHashMap<>();
        this.cash = cash;
        this.currentInvoice = null;
    }

    public boolean setInvoice(Invoice invoice) {
        if(invoice != null) {
            this.currentInvoice = invoice;
            return true;
        }
        return false;
    }

    public float getCash() {
        return cash;
    }

    public Invoice getCurrentInvoice() {
        return currentInvoice;
    }

    public void addProduct(Product product, int count) {
        if(this.products.containsKey(product)) {
            count += this.products.get(product);
        }
        this.products.put(product,count);
    }

    public boolean makePayment(){
        if(this.currentInvoice.getPrice() <= this.cash){
            this.cash -= this.currentInvoice.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", products=" + products +
                ", cash=" + cash +
                ", currentInvoice=" + currentInvoice +
                '}';
    }
}
