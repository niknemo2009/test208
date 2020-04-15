package com.parking;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ParkingTableModel extends AbstractTableModel {
    private DBController dbController;//для роботи з БД
    private PrivateParking parking;
    private ArrayList<Record> data;//всі дані з журналу записів про парковку
    private ArrayList<Record> displayedData;//дані, що відображуються в даний момент

    public ParkingTableModel(DBController dbController){
        this.dbController = dbController;

        User user = new User(dbController.getUserLogin(), dbController.getUserPassword());
        parking = new PrivateParking(user, dbController.getVolume(), dbController.getPrice());
        parking.getJournal().setRecords(dbController.getRecords());

        data = parking.getJournal().AllRecords();
        displayedData = data;
    }

    @Override
    public int getRowCount() {
        int rowCount;

        try{
            rowCount = displayedData.size();
        }catch (NullPointerException ex){
            rowCount = 0;
        }

        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Place";
            case 1: return "Licence plate";
            case 2: return "Owner";
            case 3: return "Date";
            case 4: return "Time";
            case 5: return "Action";
        }
        return "";
    }

    //Додавання та видалення даних
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record record = displayedData.get(rowIndex);
        switch(columnIndex){
            case 0: return record.getPlaceNumber();
            case 1: return  record.getCarLicencePlate();
            case 2: return record.getOwnerName();
            case 3: return record.getDate();
            case 4: return record.getTime();
            case 5: return record.getAction();
        }
        return null;
    }

    public void addData(Car car, int placeForParking, String date, String time){
        try{
            LocalDate localDate = StringToLocalDate(date);
            LocalTime localTime = StringToLocalTime(time);

            Record record = new Record(placeForParking, car.getLicencePlate(), car.getOwner(), Action.PARKING, localDate, localTime);
            dbController.addRecord(record);

            //Update model
            parking.getJournal().setRecords(dbController.getRecords());
            ArrayList<Record> journal = parking.getJournal().AllRecords();
            for(Record rec : journal){
                System.out.println(rec);
            }
            data = parking.getJournal().AllRecords();
            displayedData = data;
            //Update view
            fireTableDataChanged();
        }catch(DateTimeParseException ex){
            JOptionPane.showMessageDialog(null,"Invalid value of date or time!");
        }
    }

    public void removeData(int placeForParking, String date, String time){
        try{
            LocalDate localDate = StringToLocalDate(date);
            LocalTime localTime = StringToLocalTime(time);

            dbController.updateRecord(placeForParking, localDate, localTime);

            //Update model
            parking.getJournal().setRecords(dbController.getRecords());
            data = parking.getJournal().AllRecords();
            displayedData = data;

            //Update view
            fireTableDataChanged();
        }catch(DateTimeParseException ex){
            JOptionPane.showMessageDialog(null,"Invalid value of date or time!");
        }
    }

    //Сортування
    public void sortByOwner(){
        parking.getJournal().sortByOwner();
        fireTableDataChanged();
    }

    public void sortByCar(){
        parking.getJournal().sortByCar();
        fireTableDataChanged();
    }

    //Створення звіту
    public void createOrder(String owner, String startDate, String endDate){
        try{
            LocalDate start = StringToLocalDate(startDate);
            LocalDate end = StringToLocalDate(endDate);
            parking.getJournal().CreateOrder(owner, parking.getPriceOfPlace(), start, end);
        }catch(DateTimeParseException ex){
            JOptionPane.showMessageDialog(null, "Invalid value! Please, write dates in format d/MM/yyyy.");
        }
    }

    //Зараз на парковці
    public void nowOnParking(){
        ArrayList<Record> tempData = parking.getJournal().NowOnParking();
        if(tempData.size() > 0){
            displayedData = tempData;
            fireTableDataChanged();
        }else{
            JOptionPane.showMessageDialog(null, "There is not cars on parking now!");
        }
    }

    //Пошук записів
    public void search(ArrayList<String> dataForSearch){
        ArrayList<Record> searchedData = null;//знайдені дані

        //Пошук за кількома критеріями
        if(dataForSearch.get(0) != null){
            searchedData = searchByOwner(dataForSearch.get(0));
        }

        if(dataForSearch.get(1) != null && searchedData == null){
            searchedData = searchByLicencePlate(dataForSearch.get(1));
        }else if(dataForSearch.get(1) != null && searchedData != null){
            parking.getJournal().setRecords(searchedData);
            searchedData = searchByLicencePlate(dataForSearch.get(1));
        }

        if(dataForSearch.get(2) != null && dataForSearch.get(3) != null && searchedData == null){
            searchedData = searchByTime(dataForSearch.get(2), dataForSearch.get(3));
        }else if(dataForSearch.get(2) != null && dataForSearch.get(3) != null && searchedData != null){
            parking.getJournal().setRecords(searchedData);
            searchedData = searchByTime(dataForSearch.get(2), dataForSearch.get(3));
        }

        parking.getJournal().setRecords(data);//відновлення загальних данихі

        //Відображення результату
        displayedData = searchedData;
        fireTableDataChanged();
    }

    //Службові методи
    //Очистка форми
    public void reset(){
        displayedData = data;
        fireTableDataChanged();
    }

    //Конвертація
    private LocalDate StringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
    private LocalTime StringToLocalTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        return  LocalTime.parse(time, formatter);
    }

    //Для пошуку
    private ArrayList<Record> searchByOwner(String owner){
        ArrayList<Record> tempData = parking.getJournal().SearchByOwner(owner);
        if(tempData.size() > 0){
            return tempData;
        }else{
            return null;
        }
    }
    public ArrayList<Record> searchByLicencePlate(String licencePlate){
        ArrayList<Record> tempData = parking.getJournal().SearchByLicencePlate(licencePlate);
        if(tempData.size() > 0){
            return tempData;
        }else {
            return null;
        }
    }
    public ArrayList<Record> searchByTime(String minTime, String maxTime){
        try{
            LocalDate min = StringToLocalDate(minTime);
            LocalDate max = StringToLocalDate(maxTime);

            ArrayList<Record> tempData = parking.getJournal().RecordsInRange(min, max);
            if(tempData.size() > 0){
                return tempData;
            }else{
                return null;
            }
        }catch(DateTimeParseException ex){
            JOptionPane.showMessageDialog(null,"Invalid value! Please, write dates in format d/MM/yyyy.");
        }
        return null;
    }
}
