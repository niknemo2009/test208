package lr2;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<User, Boolean> base = new HashMap<>();
        base.put(new User("Roma", "1488"), false);
        base.put(new User("R0ma", "1418"), false);
        base.put(new User("Rama", "1448"), false);
        for (User key : base.keySet()) {
            Object obj = base.get(key);
            System.out.println("Login: " + key);
        }

        User testUser = new User("", "");
        String pass = testUser.autorisation();

        for (User key : base.keySet()) {
            Object obj = base.get(key.GetPassword());
            if (pass.equals(key.GetPassword())) {
                System.out.println("Login: " + key.GetLogin() + "\nPassword: " + key.GetPassword());
            }
        }
    }

        /*
        for(String key : base.keySet()){
            Object obj = base.get(key);
            if (password.equals(obj)) {
                System.out.println("Login: " + key + "\nPassword: " + obj);
            }
        }

        Posts p = Posts.ChiefEngineer;
        System.out.println(p.getOfficialSalary());
        System.out.println(p.getVacationDays());
        p.setOfficialSalary(8000);
        System.out.println(p.getOfficialSalary());
        p.setVacationDays(100);
        System.out.println(p.getVacationDays());

        for(Posts element : Posts.values()){
            System.out.println(element);
        }

         */
}
