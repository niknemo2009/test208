package View.ModelsTables;

import Model.Data.Entity.Order;
import Model.Data.Entity.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductsTableModel extends AbstractTableModel {

    private int countColumn = 3;
    private int countRow;
    private ArrayList<Product> productsData;
    Order currentOrder;

   public ProductsTableModel(ArrayList<Product> products) {
       this.productsData = products;
       this.productsData.sort(Comparator.comparing(Product::getName));
       this.countRow = this.productsData.size();
   }

   public ProductsTableModel(Order order) {
       this(new ArrayList<>(order.getProducts().keySet()));
       this.countColumn = 4;
       this.currentOrder = order;
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
        Product product = this.productsData.get(row);
        switch (column) {
            case 0: return product.getName();
            case 1: return product.getManufacturer();
            case 2: return product.getPrice();
            case 3: return currentOrder.getProducts().get(product);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Name";
            case 1: return "Manufacturer";
            case 2: return "Price";
            case 3: return "Count";
        }
        return "";
    }

    public Product getProduct(int index) {
       return this.productsData.get(index);
    }
}
