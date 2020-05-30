package View.Panels;

import Controller.ControllerInternetShop;
import Model.Actors.Role;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private JPanel menuPanel;
    private JButton toAllProductsBtn;
    private JButton logoutBtn;
    private JButton toCreateOrderBtn;
    private JButton toAllOrdersBtn;
    private JLabel menuLabel;

    private Container container;

    private boolean isCustomer;
    private boolean isSeller;


    public MainMenuView(ControllerInternetShop controller, Container rootContainer, JPanel rootPanel) {
        this.container = rootContainer;
        this.isCustomer = controller.getUser().getRole() == Role.CUSTOMER;
        this.isSeller = !isCustomer;

        if(this.isSeller) {
            this.toCreateOrderBtn.setVisible(false);
        }

        this.toAllProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProductsView productsView = new ProductsView(controller, container, menuPanel);
                JPanel productsPanel = productsView.getProductsPanel();

                setPanel(productsPanel);
            }
        });

        this.toCreateOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateOrderView createOrderView = new CreateOrderView(controller, container, menuPanel, null, null);
                JPanel createOrderPanel = createOrderView.getCreateOrderPanel();

                setPanel(createOrderPanel);
            }
        });

        this.toAllOrdersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OrdersView ordersView = new OrdersView(controller, container, menuPanel);
                JPanel ordersPanel = ordersView.getOrdersPanel();

                setPanel(ordersPanel);
            }
        });

        this.logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setPanel(rootPanel);
            }
        });
    }

    private void setPanel(JPanel panel) {
        container.remove(0);
        container.repaint();
        container.add(panel);
        container.revalidate();
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
