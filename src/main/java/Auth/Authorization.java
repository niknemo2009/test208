package Auth;

import Controller.ControllerInternetShop;
import Model.Actors.User;
import Model.Actors.UserStatus;
import Model.Data.DataBase;
import View.View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Authorization {
    private  ArrayList<User> usersInSystem;
    private Scanner input;
    private int login_try;
    private User currentUser;
    private DataBase dataBase;

    public Authorization(DataBase dataBase) {
        this.login_try = 0;
        this.dataBase = dataBase;
        this.usersInSystem = this.dataBase.readUsers();
        this.currentUser = new User();

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    //Авторизация
    public boolean singIn() throws AuthorizationException{
        this.input = new Scanner(System.in);
        while (this.login_try <= 7){
            this.enterLogin();
            if(this.enterPassword(this.currentUser)){
                this.currentUser.setStatus(UserStatus.AUTHORIZED);
                System.out.println("Success!");
                this.login_try = 0;
                this.input.close();
                return true;
            }
            this.login_try++;
        }
        throw new AuthorizationException("You have exhausted all attempts to log in!");
    }

    private void enterLogin() {
        while (true) {
            System.out.println("Enter login:");
            String login = input.nextLine();
            this.currentUser = this.getCurrentUser(login);
            if(!correctLogin(login) || this.currentUser == null) {
                System.out.println("Incorrect login!");
                continue;
            }
            else if(this.currentUser.getStatus() == UserStatus.BLOCKED || this.currentUser.getLoginTry() >= 4){
                System.out.println("Login: " + login + " is blocked!");
                continue;
            }
            else if(this.currentUser.getStatus() == UserStatus.AUTHORIZED){
                System.out.println("This user is already logged in!");
            }
            else{
                break;
            }
        }
    }

    private boolean enterPassword(User user){
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter password: ");
            String password = input.nextLine();
            if (user.checkPassword(password)) {
                return true;
            }
            this.currentUser.increaseLoginTry();
            System.out.println("Incorrect password!");
        }
        user.setStatus(UserStatus.BLOCKED);
        System.out.println("Login: " + user.getLogin() + " is blocked!");
        return false;
    }

    //Проверка логина
    private boolean correctLogin(String login){
        return Pattern.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",login);
    }

    private User getCurrentUser(String login){
        for (User user : this.usersInSystem) {
            if(login.equals(user.getLogin())){
                return user;
            }
        }
        return null;
    }
}
