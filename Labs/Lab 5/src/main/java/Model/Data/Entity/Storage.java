package Model.Data.Entity;

import Model.Data.Entity.Product;

import java.util.*;
import java.util.function.Predicate;

public class Storage {
    private HashMap<Product, Integer> products;

    public Storage() {
        this.products = new HashMap<>(0);
    }

    public Storage(HashMap<Product, Integer> initState) {
        this();
        if(initState != null) {
            this.products.putAll(initState);
        }
    }

    public HashMap<Product, Integer> getStorageData() {
        return this.products;
    }

    public boolean collapse(Storage otherStorage) {
        if(otherStorage != null) {
            for (Map.Entry<Product, Integer> data : otherStorage.getStorageData().entrySet()) {
                int count = data.getValue();
                if(this.products.containsKey(data.getKey())) {
                    count += this.products.get(data.getKey());
                }
                this.products.put(data.getKey(), count);
            }
        }
        return false;
    }

    //Коллекция продуктов
    public Set<Product> getProducts() {
        return this.products.keySet();
    }

    //Коллекция продуктов по предикату (для поиска)
    public Set<Product> getProducts(Predicate<Product> predicate) {
        Set<Product> matchesFound = new HashSet<Product>(0);
        this.products.keySet().forEach(product -> {
            if(predicate.test(product)) {
                matchesFound.add(product);
            }
        });
        return matchesFound;
    }

    //Продукт по названию
    public Product getProduct(String name, String manufacturer) {
        if(name != null && !name.isEmpty()){
            for (Product product : this.products.keySet()) {
                if(product.getName().equals(name) && product.getManufacturer().equals(manufacturer)) {
                    return product;
                }
            }
        }
        return null;
    }

    //Список названий продуктов
    public ArrayList<String> getProductsNames() {
        HashSet<String> names = new HashSet<>();
        this.products.keySet().forEach(product -> {
            names.add(product.getName());
        });
        return new ArrayList<>(names);
    }

    //Добавить продукт в хранилище
    public boolean addProduct(Product newProduct, int count){
        if(newProduct != null) {
            if(this.products.containsKey(newProduct)){
                count += this.products.get(newProduct);
            }
            this.products.put(newProduct, count);
            return true;
        }
        return false;
    }


    public Map.Entry<Product, Integer> extractProduct(Product product, int count){
        if(product != null && contain(product,count)){
            count = this.products.get(product) - count;
            this.products.put(product, count);
            return new AbstractMap.SimpleEntry<>(product, count);
        }
        return null;
    }

    //Наличие продукта
    public boolean contain(Product product, int count){
        if(product != null) {
            return this.products.containsKey(product) && (this.products.get(product) >= count);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "products=" + this.products +
                '}';
    }
}
