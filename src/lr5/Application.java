package lr5;
import javax.management.openmbean.OpenDataException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.io.InvalidObjectException;
import java.sql.*;
import java.util.ArrayList;

public class Application extends JFrame {
    static Connection connection;
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
     void ReadAuto() throws SQLException {
        //statement интерфейс для обработки запросов
        Statement statement = connection.createStatement();
        String first = "Select * from Auto;";
        //ResultSet это класс предоставляет построчный доступ к результатам запросов
        ResultSet resultSet = statement.executeQuery(first);
        while(resultSet.next()){
            String m = resultSet.getString("month");
             Parking.info.add(new Parking(m,
                     resultSet.getString("car"),
                     resultSet.getInt("countOfParking"),
                     resultSet.getString("person"),
                     resultSet.getDouble("payment")));
         }
     }
     void ReadStatus() throws SQLException {
         Statement statement = connection.createStatement();
         String first = "Select * from CarsOnParking;";
         ResultSet resultSet = statement.executeQuery(first);
         while(resultSet.next()){
             Parking.tempCars.add(new Parking(resultSet.getString("name")));
         }
     }
     //Использовалось при инициализации базы данных
     /*void InsertAuto(Month month,String car, int countOfParking, String person, double payment) throws SQLException {
         String query="insert into Auto(month, car, countOfParking, person, payment) " +
                 "values ('" + month + "','" + car + "',"+ countOfParking + ", '" + person + "', " + payment+ ");";
         Statement statement = connection.createStatement();
         statement.executeUpdate(query);

         System.out.println("info added");
     }
    void InsertStatus(String car) throws SQLException {
        String query="insert into CarsOnParking( name) " +
                "values ('" + car + "');";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        System.out.println("info added");
    }
     void DeleteAuto() throws SQLException {
        String query="delete from Auto;";
         Statement statement = connection.createStatement();
         statement.executeUpdate(query);

         System.out.println("info deleted");
     }*/
     //JDBC-Java Database Connectivity
    static void OpenDataBase(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\users.db");
            System.out.println("Connected");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Application() throws SQLException {
        OpenDataBase();
        setSize(1280,720);
        setTitle("Parking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        setVisible(true);

        parking.statusOfCar("onParking");
        parking.parking(12,5);
        ReadAuto();
        ReadStatus();

        createHeaders();
        for(Parking item: Parking.info){
            addData(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
            //InsertInfo(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
            //InsertAuto(item.currentMonth, item.carModel, item.countOfParkingTimes, item.namePerson, item.monthPayment);
        }
        for(Parking item: Parking.tempCars){
            //InsertStatus(item.carModel);
        }
        //DeleteAuto();
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


