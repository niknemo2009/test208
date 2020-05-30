package Controller;

import Model.Actors.User;
import Model.Data.Model;
import View.*;

public class ControllerInternetShop {
    private Model model;
    private InternetShopFrame appFrame;

    private User userInSession;

    public ControllerInternetShop(Model model){
        this.model = model;
        this.userInSession = new User();
    }

    public Model getModel() {
        return this.model;
    }

    public void start() {
        this.appFrame = new InternetShopFrame(this);
    }

    public void setUser(User user) {
        this.userInSession = user;
    }

    public User getUser() {
        return userInSession;
    }
}
