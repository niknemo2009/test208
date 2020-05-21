package Controller;

import Auth.Authorization;
import Auth.AuthorizationException;
import Model.Actors.User;
import Model.Data.Model;
import View.View;

public class ControllerInternetShop {
    private View view;
    private Model model;

    public ControllerInternetShop(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

}
