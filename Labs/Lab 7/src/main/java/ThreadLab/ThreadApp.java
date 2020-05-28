package ThreadLab;

import ThreadLab.entities.Client;
import ThreadLab.entities.Invoice;
import ThreadLab.entities.Product;
import ThreadLab.entities.Shop;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ThreadApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Shops
        ArrayList<Shop> shops = new ArrayList<Shop>();
        shops.add(new Shop("Eldorado", "1111 2222 3333 4444"));
        shops.add(new Shop("Foxtrot", "1112 2322 3133 4487"));
        shops.add(new Shop("Comfy", "7311 5232 5343 6234"));

        //Products data
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Telephone", 2000f));
        products.add(new Product("Computer", 7000f));
        products.add(new Product("Televisor", 10000f));

        //Shops data
        shops.get(0).addProduct(products.get(0), 2);
        shops.get(0).addProduct(products.get(1), 10);
        shops.get(0).addProduct(products.get(2), 10);

        shops.get(1).addProduct(products.get(0), 1);
        shops.get(1).addProduct(products.get(1), 3);
        shops.get(1).addProduct(products.get(2), 5);

        shops.get(2).addProduct(products.get(0), 1);
        shops.get(2).addProduct(products.get(1), 1);
        shops.get(2).addProduct(products.get(2), 15);

        //Client
        Client mike = new Client("mike", 150000f);
        //System.out.println("Init state Client: " + mike);

        //Executors
        ThreadApp program = new ThreadApp();
        ExecutorService executor = Executors.newWorkStealingPool();

        ArrayList<Callable<Invoice>> tasks = new ArrayList<>();
        tasks.add(program.createTask(shops.get(0), products.get(2), 1)); //1 компютер
        tasks.add(program.createTask(shops.get(1), products.get(2), 1)); //1 компютер
        tasks.add(program.createTask(shops.get(2), products.get(2), 1)); //1 компютер

        Invoice resultInvoice = executor.invokeAny(tasks);


        if(resultInvoice == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println(resultInvoice);
            mike.setInvoice(resultInvoice);
            shops.forEach(shop -> {
                if(shop.getName().equals(resultInvoice.getShopName())){
                    shop.completeInvoice(mike);
                    //System.out.println(shop);
                }
            });
            System.out.println(mike);
        }


    }

    public Callable<Invoice> createTask(Shop shop, Product product, int count) {
        return () ->{
            Thread.currentThread().setName(shop.getName());
            if(!shop.checkProduct(product, count)){
                TimeUnit.SECONDS.sleep(2);
            }
            System.out.println(shop.getName());
            return shop.createInvoice(product, count);
        };
    }
}
