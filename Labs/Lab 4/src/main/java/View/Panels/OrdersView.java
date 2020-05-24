package View.Panels;

import Controller.ControllerInternetShop;
import Model.Actors.Role;
import Model.Actors.User;
import Model.Data.Entity.Order;
import Model.Data.Model;
import View.ModelsTables.OrdersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdersView {
    private JPanel ordersPanel;
    private JTable ordersTable;
    private JButton goBackBtn;
    private JButton completeOrderBtn;
    private JButton toInfoBtn;
    private JButton deleteOrder;
    private JLabel titleLabel;

    private Container container;
    private Model model;
    private User user;
    private OrdersTableModel tableData;
    boolean isCustomer;


    public OrdersView(ControllerInternetShop controller, Container container, JPanel rootPanel) {
        this.container = container;
        this.model = controller.getModel();
        this.user = controller.getUser();
        this.isCustomer= user.getRole() == Role.CUSTOMER;

        this.updateTableView();

        this.toInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ordersTable.getSelectedRowCount() == 1) {
                    int row = ordersTable.getSelectedRow();
                    OrderInfo infoView = new OrderInfo(controller, container, ordersPanel, tableData.getOrder(row));
                    JPanel infoPanel = infoView.getOrderInfoPanel();

                    setPanel(infoPanel);
                } else {
                    JOptionPane.showMessageDialog(null, "Only one entry can be selected!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.deleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ordersTable.getSelectedRowCount() == 1) {
                    int row = ordersTable.getSelectedRow();
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the order?",
                            "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(answer == JOptionPane.YES_OPTION) {
                        Order order =  tableData.removeOrder(row);

                        if(model.removeOrder(order.getId())) {
                            JOptionPane.showMessageDialog(null, "Order successfully deleted!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete order!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        updateTableView();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Only one entry can be selected!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.completeOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(ordersTable.getSelectedRowCount() == 1) {
                    int row = ordersTable.getSelectedRow();
                    int answer = JOptionPane.showConfirmDialog(null, "Confirm the complete order!",
                            "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(answer == JOptionPane.YES_OPTION) {
                        Order order = tableData.getOrder(row);
                        if(model.completeOrder(order.getId())){
                            updateTableView();
                            JOptionPane.showMessageDialog(null, "Order successfully completed!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "An error occurred while completing the order!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        this.goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setPanel(rootPanel);
            }
        });
    }

    public JPanel getOrdersPanel() {
        return ordersPanel;
    }

    private void updateTableView() {
        ArrayList<Order> orders = this.uploadOrders();
        this.ordersTable.removeAll();
        this.tableData = new OrdersTableModel(orders);
        this.ordersTable.setModel(tableData);
    }

    private ArrayList<Order> uploadOrders(){
        if(isCustomer) {
            this.completeOrderBtn.setVisible(false);
            return this.model.getOrders(this.user.getId());
        } else {
            this.deleteOrder.setVisible(false);
            return this.model.getOrders();
        }
    }

    private void setPanel(JPanel panel) {
        this.container.remove(0);
        this.container.repaint();
        this.container.add(panel);
        this.container.revalidate();
    }

}
