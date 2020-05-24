package View.ModelsTables;

import Model.Data.Entity.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrdersTableModel extends AbstractTableModel {

    private int countRow;
    private int countColumn;
    private ArrayList<Order> ordersData;

    public OrdersTableModel(ArrayList<Order> orders) {
        this.ordersData = orders;
        this.countRow = ordersData.size();
        this.countColumn = 4;
    }

    @Override
    public int getRowCount() {
        return countRow;
    }

    @Override
    public int getColumnCount() {
        return countColumn;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Order order = this.ordersData.get(row);
        switch (column) {
            case 0: return order.getId();
            case 1: return order.getCustomerId();
            case 2: return order.calculatePrice();
            case 3: return order.getStatus();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Order ID";
            case 1: return "Customer ID";
            case 2: return "Total Price";
            case 3: return "Order Status";
        }
        return "";
    }

    public Order removeOrder(int index) {
        return this.ordersData.remove(index);
    }

    public Order getOrder(int index) {
        return this.ordersData.get(index);
    }
}
