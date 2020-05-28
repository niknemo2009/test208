package ThreadLab.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Shop {
    private String name;
    private Map<Product, Integer> products;
    private String bankAccount;
    private float cash;

    public Shop(String name, String bankAccount) {
        this.name = name;
        this.cash = 0f;
        this.bankAccount = bankAccount;
        this.products = new ConcurrentHashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public boolean completeInvoice( Client client) {
        Invoice invoice = client.getCurrentInvoice();
        if(invoice != null &&
                !invoice.isCompleted() &&
                client.makePayment() &&
                this.replenishAccount(invoice.getPrice(), invoice.getBankAccount())) {
            this.products.put(invoice.getProduct(),
                    this.products.get(invoice.getProduct()) - invoice.getCount());
            invoice.complete();
            client.addProduct(invoice.getProduct(), invoice.getCount());
            return true;
        }
        return false;
    }

    public Invoice createInvoice(Product product, int count) {
        if(this.checkProduct(product, count)){
            return new Invoice(product,count, product.getPrice(), this.bankAccount, this.name);
        }
        return null;
    }

    public void addProduct(Product product, int count) {
        if(this.products.containsKey(product)) {
            count += this.products.get(product);
        }
        this.products.put(product,count);
    }

    public boolean checkProduct(Product product, int count) {
        return this.products.containsKey(product) && this.products.get(product) >= count;
    }

    private boolean replenishAccount(float cash, String bankAccount) {
        if(this.bankAccount == bankAccount) {
            this.cash += cash;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", products=" + products +
                ", bankAccount=" + bankAccount +
                ", cash=" + cash +
                '}';
    }



}
