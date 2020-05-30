package View.Panels;

import Controller.ControllerInternetShop;
import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.Data.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateOrderView {
    private JLabel titleLabel;
    private JComboBox selectNameProduct;
    private JComboBox selectManufacturerProduct;
    private JTextField countProductsField;
    private JButton addProductBtn;
    private JButton goBackBtn;
    private JLabel nameLabel;
    private JLabel manufacturerLabel;
    private JLabel countLabel;
    private JFormattedTextField totalPriceField;
    private JLabel totalPriceLabel;
    private JPanel createOrderPanel;
    private JButton addOrderBtn;

    Order order;

    public CreateOrderView(ControllerInternetShop controller, Container container, JPanel rootPanel, Order currOrder, OrderInfo infoView) {

        Model model = controller.getModel();

        boolean isEdit = currOrder != null;

        if(isEdit){
            this.order = currOrder;
            this.totalPriceField.setValue(this.order.calculatePrice());
        } else {
            this.order = new Order(controller.getUser().getId());
        }

        this.totalPriceField.setForeground(Color.RED);

        //Selectors data
        this.selectNameProduct.addItem("");
        model.getProductsList().forEach(name -> {
            this.selectNameProduct.addItem(name);
        });

        this.selectManufacturerProduct.setEnabled(false);

        this.selectNameProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = selectNameProduct.getSelectedItem().toString();
                if(!name.isEmpty()) {
                    model.getManufacturers(name).forEach(manufacturer -> {
                        selectManufacturerProduct.addItem(manufacturer);
                    });
                    selectManufacturerProduct.setEnabled(true);
                }
            }
        });

        this.addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the product to order",
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(answer == JOptionPane.YES_OPTION) {
                    //Data
                    Object nameData = selectNameProduct.getSelectedItem();
                    Object manufacturerData = selectManufacturerProduct.getSelectedItem();

                    if(nameData != null && manufacturerData != null) {
                        try {
                            int count = Integer.parseInt(countProductsField.getText());
                            Product product = model.getProduct(nameData.toString(), manufacturerData.toString());

                            if(product != null && model.checkProduct(product, count)) {
                                if(infoView != null){
                                    model.addProductToOrder(order, product, count);
                                } else {
                                    order.addProduct(product, count);
                                }
                                totalPriceField.setValue(order.calculatePrice());
                                JOptionPane.showMessageDialog(null, "Product added to order!",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                resetFields();
                            } else {
                                JOptionPane.showMessageDialog(null, "Not enough products in storage!",
                                        "Warning", JOptionPane.QUESTION_MESSAGE);
                            }

                        } catch (NumberFormatException  ex) {
                            JOptionPane.showMessageDialog(null, "Count must be a numerical value!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You did not fill in all the fields!",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

        this.addOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the product to order!",
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(answer == JOptionPane.YES_OPTION) {
                    if(!order.getProducts().isEmpty()) {
                        boolean result = true;
                        if(!isEdit){
                            result = model.addOrder(order);
                        }
                        if(result) {
                            JOptionPane.showMessageDialog(null, "Order added successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            goBackBtn.doClick();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error adding order!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Order cannot be empty!",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        this.goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(0);
                container.repaint();
                container.add(rootPanel);
                container.revalidate();
                if(infoView != null) {
                    infoView.updateView(order, order.getStatus());
                }
            }
        });
    }

    private void resetFields() {
        this.selectNameProduct.setSelectedIndex(0);
        this.selectManufacturerProduct.removeAllItems();
        this.selectManufacturerProduct.setEnabled(false);
        this.countProductsField.setText(null);
    }

    public JPanel getCreateOrderPanel() {
        return createOrderPanel;
    }
}
