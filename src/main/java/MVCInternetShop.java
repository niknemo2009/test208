import Auth.Authorization;
import Auth.AuthorizationException;
import Model.*;
import Model.Actors.User;
import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.Data.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class MVCInternetShop {
    public static void main(String[] args) {
        //DEMONSTRATION model

        User user = new User();
        Model model = new FileModelInternetShop();
        Authorization auth = new Authorization(model);

        try {
            auth.singIn();

        } catch (AuthorizationException ex) {
            System.out.println(ex.getMessage());
        }

        user = auth.getCurrentUser();
    }
}
