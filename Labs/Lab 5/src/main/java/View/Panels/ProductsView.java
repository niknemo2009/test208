package View.Panels;

import Controller.ControllerInternetShop;
import Model.Data.Entity.Product;
import Model.Data.Model;
import View.ModelsTables.ProductsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductsView {
    private JPanel productsPanel;
    private JLabel titleLabel;
    private JTable productsTable;
    private JButton goBack;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchNameBtn;
    private JButton searchManufacturerBtn;
    private JButton showAllBnt;

    private ProductsTableModel tableModel;

    public ProductsView(ControllerInternetShop controller, Container container, JPanel patentPanel) {
        Model model = controller.getModel();

        //Table
        this.tableModel = new ProductsTableModel(model.getAllProducts());
        this.productsTable.setModel(tableModel);

        this.showAllBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchField.setText(null);
                searchAction(model.getAllProducts(), "No products in storage!");
            }
        });

        this.searchManufacturerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String manufacturer = searchField.getText();

                if(!manufacturer.isEmpty()) {
                    ArrayList<Product> products = model.findByManufacturer(manufacturer);
                    searchAction(products, "There are no products from this manufacturer in storage!");
                }
            }
        });

        this.searchNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = searchField.getText();

                if(!name.isEmpty()) {
                    ArrayList<Product> products = model.findByName(name);
                    searchAction(products, "There are no products with this name in storage!");
                }
            }
        });

        this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.remove(0);
                container.repaint();
                container.add(patentPanel);
                container.revalidate();
            }
        });

    }

    private void searchAction(ArrayList<Product> data, String errMessage) {
        if(!data.isEmpty()) {
            productsTable.removeAll();
            tableModel = new ProductsTableModel(data);
            productsTable.setModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(null, errMessage);
        }
    }

    public JPanel getProductsPanel() {
        return productsPanel;
    }
}
