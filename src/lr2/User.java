package lr2;

import java.util.Scanner;



public class User {
    String login;
    String password;

    public User(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public String GetLogin(){
        return login;
    }
    public String GetPassword(){
        return password;
    }


    public String autorisation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input login: ");
        this.login  = sc.nextLine();
        System.out.println("Input password: ");
        this.password  = sc.nextLine();
        sc.close();
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
