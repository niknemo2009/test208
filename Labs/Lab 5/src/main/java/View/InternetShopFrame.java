package View;

import Auth.Authorization;
import Controller.ControllerInternetShop;
import View.Panels.AuthorizationView;
import View.Panels.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InternetShopFrame extends JFrame {
    private Container container;
    private Toolkit toolkit;
    private ControllerInternetShop controller;


    public InternetShopFrame(ControllerInternetShop controller) {
        super("Internet Shop");
        this.controller = controller;
        this.toolkit = Toolkit.getDefaultToolkit();

        //Установка параметров главного окна
        Dimension dimension = this.toolkit.getScreenSize();
        this.setBounds(dimension.width/2 - (1000/2), dimension.height/2 - (600/2), 1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setResizable(false);



        //Nнициализация контейнера
        this.container = this.getContentPane();
        this.container.setLayout(new BorderLayout());
        this.container.setPreferredSize(new Dimension(1000,600));

        //Установка стартовой панели (Авторизация)
        AuthorizationView authView = new AuthorizationView();
        JPanel panel = authView.getAuthorizationPanel();
        JButton button = authView.getLogInButton();
        this.container.add(panel);
        this.container.revalidate();

        //Авторизация
        Authorization auth = new Authorization(this.controller.getModel());

        //Event Listener
        button.addActionListener(actionEvent -> {
            String login = authView.getLoginField().getText().trim();
            String password = new String(authView.getPasswordField().getPassword());
            if(auth.singIn(login, password)) {
                controller.setUser(auth.getCurrentUser());

                MainMenuView menuView = new MainMenuView(controller, container, panel);
                JPanel menuPanel = menuView.getMenuPanel();

                //Set menu panel
                container.remove(panel);
                container.repaint();
                container.add(menuPanel);
                container.revalidate();
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect login or password!");
            }
            authView.reset();
        });


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                controller.getModel().finalization();
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

    }



}
