import Controller.ControllerInternetShop;
import Model.*;
import Model.Data.Model;

public class MVCInternetShop {
    public static void main(String[] args) {

        //Model
        Model model = new MySQLModelInternetShop();

        //Controller
        ControllerInternetShop appInternetShop = new ControllerInternetShop(model);

        //Start app
        appInternetShop.start();
    }
}
