package View.Panels;

import Controller.ControllerInternetShop;
import Model.Actors.Role;
import Model.Actors.User;
import Model.Data.Entity.Order;
import Model.Data.Entity.OrderStatus;
import View.ModelsTables.ProductsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderInfo {
    private JPanel orderInfoPanel;
    private JTable orderProductsTable;
    private JFormattedTextField totalPriceField;
    private JFormattedTextField statusField;
    private JButton deleteProductBtn;
    private JButton addProductsBtn;
    private JLabel titleLabel;
    private JLabel totalPriceLabel;
    private JLabel statusLabel;
    private JButton goBackBtn;

    private Container container;
    private ProductsTableModel productsTableData;


    public OrderInfo(ControllerInternetShop controller, Container container, JPanel rootPanel, Order order) {
        User user = controller.getUser();
        boolean isCustomer = user.getRole() == Role.CUSTOMER;
        boolean isCompleted = order.getStatus() == OrderStatus.COMPLETED;
        this.container = container;

        //Edit data buttons
        if(!isCustomer || isCompleted){
            this.deleteProductBtn.setVisible(false);
            this.addProductsBtn.setVisible(false);
        }

        //DATA
        this.updateView(order, order.getStatus());

        //Title data
        this.titleLabel.setText("Order info: " + order.getId());


        this.deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(orderProductsTable.getSelectedRowCount() == 1) {
                    int row = orderProductsTable.getSelectedRow();
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the product from order?",
                            "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(answer == JOptionPane.YES_OPTION) {
                        controller.getModel().removeProductFormOrder(order, productsTableData.getProduct(row));
                        updateView(order, order.getStatus());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Only one entry can be selected!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.addProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateOrderView editOrderView = new CreateOrderView(controller, container, orderInfoPanel, order, getView());
                JPanel editOrderPanel = editOrderView.getCreateOrderPanel();

                setPanel(editOrderPanel);
            }
        });

        this.goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               setPanel(rootPanel);
            }
        });
    }

    public OrderInfo getView() {
        return this;
    }

    public JPanel getOrderInfoPanel() {
        return orderInfoPanel;
    }

    public void updateView(Order order, OrderStatus flagView) {
        this.statusField.setValue(order.getStatus());
        if(flagView == OrderStatus.COMPLETED){
            this.statusField.setForeground(Color.GREEN);
        } else {
            this.statusField.setForeground(Color.BLUE);
        }

        this.totalPriceField.setValue(order.calculatePrice());
        this.totalPriceField.setForeground(Color.RED);

        this.updateTableView(order);
    }

    private void setPanel(JPanel panel) {
        container.remove(0);
        container.repaint();
        container.add(panel);
        container.revalidate();
    }

    private void updateTableView(Order order) {
        this.productsTableData = new ProductsTableModel(order);
        this.orderProductsTable.removeAll();
        this.orderProductsTable.setModel(productsTableData);
    }
}
