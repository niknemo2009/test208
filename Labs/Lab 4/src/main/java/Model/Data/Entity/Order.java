package Model.Data.Entity;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Order {
    private static long orders_count = 0;

    private long id;
    private long customerId;
    private HashMap<Product, Integer> products;
    private OrderStatus status;

    public Order(long customerId, HashMap<Product, Integer> customProducts) {
        this.status = OrderStatus.NOTCOMPLETED;
        this.products = new HashMap<>();
        if(customProducts != null && !customProducts.isEmpty()) {
            products.putAll(customProducts);
        }
        this.customerId = customerId;
        this.id = ++Order.orders_count;
    }

    public Order(long customerId) {
        this(customerId, null);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public HashMap<Product, Integer> getProducts() {
        return this.products;
    }

    //Добавить продукт в заказ
    public boolean addProduct(Product product, int count) {
        if(product != null) {
            if (this.products.containsKey(product)) {
                count += this.products.get(product);
            }
            this.products.put(product, count);
            return true;
        }
        return false;
    }

    public float calculatePrice() {
        AtomicReference<Float> result = new AtomicReference<>(0f);
        this.products.forEach((product, count) -> {
            result.updateAndGet(v -> (float) (v + (product.getPrice() * count)));
        });
        return result.get();
    }

    //Удалить продукт
    public boolean removeProduct(Product product) {
        if(product != null && this.products.containsKey(product)) {
            this.products.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return this.id == order.id &&
                this.customerId == order.customerId &&
                this.products.equals(order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.customerId, this.products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.id +
                ", customerId=" + this.customerId +
                ", products=" + this.products +
                "}";
    }
}

