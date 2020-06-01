package lr4;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.io.InvalidObjectException;
import java.util.ArrayList;

public class Application extends JFrame {
    NameComparator nameComparator = new NameComparator();
    CarsComparator carsComparator = new CarsComparator();
    Parking parking = new Parking();
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel1;
     JTable dataTable;
    private JPanel panel;
    private JButton sortByCarButton;
    private JButton sortByNameButton;
    private JTable statusTable;
    private JButton statusButton;
    private JButton searchPersonButton;
    private JButton searchCarButton;
    private JTextField searchPersonTextField;
    private JTextField searchCarTextField;
    private JButton restoreButton;

    private void createHeaders(){
        defaultTableModel = (DefaultTableModel)dataTable.getModel();
        defaultTableModel.addColumn("Month");
        defaultTableModel.addColumn("Car");
        defaultTableModel.addColumn("Count of parking");
        defaultTableModel.addColumn("Person");
        defaultTableModel.addColumn("Payment");
        defaultTableModel1 = (DefaultTableModel)statusTable.getModel();
        defaultTableModel1.addColumn("Car on parking");
    }


    private void addData( Month month,String car, Integer countOfParking, String person, Double payment){
        String[] strRow = {month.toString(), car, countOfParking.toString(), person, payment.toString()};
        defaultTableModel.addRow(strRow);
    }
    private void addStatus(String carModel){
        String[] strRow = {carModel};
        defaultTableModel1.addRow(strRow);
    }
    public Application(){
        setSize(1280,720);
        setTitle("Parking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        setVisible(true);


        parking.parking(12,5);
        createHeaders();
        for(Parking item: Parking.info){
            addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
        }

        EventListener eventListener = new EventListener();
        sortByCarButton.addActionListener(eventListener);
        statusButton.addActionListener(eventListener);
        sortByNameButton.addActionListener(eventListener);
        searchCarButton.addActionListener(eventListener);
        searchPersonButton.addActionListener(eventListener);
        restoreButton.addActionListener(eventListener);

    }
    public class EventListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == sortByNameButton){
                while(defaultTableModel.getRowCount()>0){
                    defaultTableModel.removeRow(0);
                }
                ArrayList<Parking> sorted;
                sorted = parking.sortParking(12,nameComparator);
                for(Parking item: sorted){
                    addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
                }
            }
            if(actionEvent.getSource() == sortByCarButton){
                while(defaultTableModel.getRowCount()>0){
                    defaultTableModel.removeRow(0);
                }
                ArrayList<Parking> sorted;
                sorted = parking.sortParking(12,carsComparator);
                for(Parking item: sorted){
                    addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
                }
            }
            if(actionEvent.getSource() == statusButton){
                ArrayList<Parking> sorted;
                sorted = parking.statusOfCar("onParking");
                for(Parking item: sorted){
                    addStatus(item.carModel);
                    System.out.println(item.carModel);
                }
            }
            if(actionEvent.getSource() == searchCarButton){
                while(defaultTableModel.getRowCount()>0){
                    defaultTableModel.removeRow(0);
                }
                ArrayList<Parking> reportParking;
                reportParking = parking.report("auto", searchCarTextField.getText());
                for(Parking item: reportParking){
                    addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
                }
            }
            if(actionEvent.getSource() == searchPersonButton){
                while(defaultTableModel.getRowCount()>0){
                    defaultTableModel.removeRow(0);
                }
                ArrayList<Parking> reportParking;
                reportParking = parking.report("person", searchPersonTextField.getText());
                for(Parking item: reportParking){
                    addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
                }
            }
            if(actionEvent.getSource() == restoreButton){
                while(defaultTableModel.getRowCount()>0){
                    defaultTableModel.removeRow(0);
                }
                for(Parking item: Parking.info){
                    addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
                }
            }
        }
    }
}


