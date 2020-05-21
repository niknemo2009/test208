package Model.Data.Entity;

import java.util.Objects;

public class Product {
    private String name;
    private String manufacturer;
    private float price;

    public Product(String name, String manufacturer, float price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Product(String name, String manufacturer) {
        this(name, manufacturer, 0f);
    }

    public Product() {
        this("", "", 0.0f);
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Product setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Product setPrice(float price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, this.price) == 0 &&
                this.name.equals(product.name) &&
                this.manufacturer.equals(product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.manufacturer, this.price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", price=" + this.price +
                '}';
    }
}
