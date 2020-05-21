package Model;

import Model.Actors.Role;
import Model.Actors.User;
import Model.Data.Entity.Product;
import Model.Data.Entity.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileModelInternetShop extends ModelInternetShop {
    private final String addressProductData = "./src/main/resources/storage_data_base.txt";
    private final String addressUsersData = "./src/main/resources/users.txt";

    @Override
    public HashMap<Product, Integer> readDataBase(){
        HashMap<Product, Integer> data = new HashMap<>(0);
        try {
            Scanner input = new Scanner(new File(addressProductData));
            while(input.hasNextLine()){
                data.put(new Product(input.next(), input.next(), input.nextFloat()), input.nextInt());
            }
            input.close();
        } catch (FileNotFoundException ex) {
            System.err.println("ReadProducts::A FileNotFoundException was caught!");
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File(addressUsersData));
            while (input.hasNextLine()) {
                User user = new User();
                if(user.createUses(input.nextLong(), input.next(), input.next(), Role.valueOf(input.next()))) {
                    users.add(user);
                }
            }
            input.close();
        } catch (FileNotFoundException ex) {
            System.err.println("ReadUser::A FileNotFoundException was caught!");
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean writeDataBase(Storage storage) {
        try(FileWriter writer = new FileWriter(addressProductData,false)) {
            if(storage != null) {
                for (Map.Entry<Product, Integer> data : storage.getStorageData().entrySet()) {
                    writer.write(data.getKey().getName() + " " +
                            data.getKey().getManufacturer() + " " +
                            data.getKey().getPrice() + " " +
                            data.getValue());
                }
            }
            else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.err.println("ReadUser::A FileNotFoundException was caught!");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("ReadUser::A IOException was caught!");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
