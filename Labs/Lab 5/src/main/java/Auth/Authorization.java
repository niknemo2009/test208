package Auth;

import Model.Actors.User;
import Model.Data.DataBase;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Authorization {
    private ArrayList<User> usersInSystem;
    private User currentUser;
    private DataBase dataBase;

    public Authorization(DataBase dataBase) {
        this.dataBase = dataBase;
        this.usersInSystem = this.dataBase.readUsers();
        this.currentUser = new User();

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    //Авторизация
    public boolean singIn(String login, String password) {
        if(login != null && password != null && this.correctLogin(login)) {
            this.currentUser = this.getCurrentUser(login);
            if(this.currentUser != null) {
                if (this.currentUser.checkPassword(password)) {
                    return true;
                }
            }
        }
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
