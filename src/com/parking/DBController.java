package com.parking;


import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBController {
    int currentParkingId;
    private Connection connection;

    public DBController(String HOST, String USERNAME, String PASSWORD){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        }catch(SQLException ex){
            System.out.println("Помилка підключення до БД!");
        }catch (ClassNotFoundException ex){
           ex.getMessage();
        }
    }

    public void setCurrentParkingId(int currentParkingId) {
        this.currentParkingId = currentParkingId;
    }

    //METHODS FOR USING
    public ArrayList<Record> getRecords(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM records";
            ResultSet result = statement.executeQuery(query);

            ArrayList<Record> records = new ArrayList<Record>();
            Record record;

            while(result.next()){
                //id of parking
                int CurrentParkingId = result.getInt("id_parking");

                if(CurrentParkingId == currentParkingId){
                    //Time of parking
                    String parkingTime = result.getString("parking_time");
                    LocalTime parkingLocalTime = StringToLocalTime(parkingTime);
                    String parkingDate = result.getString("parking_date");
                    LocalDate parkingLocalDate = StringToLocalDate(parkingDate);

                    //Time of driving
                    String drivingTime = result.getString("driving_time");
                    LocalTime drivingLocalTime = null;
                    if(drivingTime != null){
                        drivingLocalTime = StringToLocalTime(drivingTime);
                    }
                    String drivingDate = result.getString("driving_date");
                    LocalDate drivingLocalDate = null;
                    if(drivingDate != null){
                        drivingLocalDate = StringToLocalDate(drivingDate);
                    }

                    //Get licence plate of car by id
                    int idCar = result.getInt("id_car");
                    PreparedStatement preparedStatementForCar = connection.prepareStatement("SELECT * FROM cars WHERE id=?");
                    preparedStatementForCar.setInt(1, idCar);
                    ResultSet carResultSet = preparedStatementForCar.executeQuery();
                    String licencePlate = null;

                    while(carResultSet.next()){
                        licencePlate = carResultSet.getString("licence_plate");
                    }

                    //Get owner name by id
                    int idOwner = result.getInt("id_owner");
                    PreparedStatement preparedStatementForOwner = connection.prepareStatement("SELECT * FROM owners WHERE id=?");
                    preparedStatementForOwner.setInt(1, idOwner);
                    ResultSet ownerResultSet = preparedStatementForOwner.executeQuery();

                    String name = null;
                    String surname = null;
                    String fio = null;
                    while(ownerResultSet.next()){
                        name = ownerResultSet.getString("name");
                        surname = ownerResultSet.getString("surname");
                        fio = name + " " + surname;
                    }

                    //Place number
                    int placeNumber = result.getInt("place_number");

                    record = new Record(placeNumber, licencePlate, fio, Action.PARKING, parkingLocalDate, parkingLocalTime);
                    records.add(record);
                    if(drivingDate != null && drivingTime != null){
                        record = new Record(placeNumber, licencePlate, fio, Action.MOVING, drivingLocalDate, drivingLocalTime);
                        records.add(record);
                    }
                }
            }

            return records;
        }catch(SQLException ex){
            ex.getMessage();
            return null;
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public void addRecord(Record record){
        //Place number - to table "records"
        int placeNumber = record.getPlaceNumber();

        //Licence plate - to table "cars"
        String licencePlate = record.getCarLicencePlate();

        //name and surname - to table "owners"
        String fio = record.getOwnerName();
        String[] fioArr = fio.split(" ", 2);
        String name = fioArr[0];
        String surname = fioArr[1];

        //time - to table "records"
        LocalTime parkingTimeStr = record.getTime();
        //date - to table "owners"
        LocalDate parkingDateStr = record.getDate();

        //Insert data to table owners/use data about owners
        int ownerId = searchByFio(name, surname);
        if(ownerId == 0){
            insertOwner(name, surname);
            ownerId = searchByFio(name, surname);
        }

        //Insert data to table cars/use data from table cars
        int carId = searchByLicencePlate(licencePlate);
        if(carId == 0){
            insertCar(licencePlate);
            carId = searchByLicencePlate(licencePlate);
        }

        System.out.println("Car: " + carId + ", owner: " + ownerId);
        //Insert/update data in table records
        insertRecord(parkingDateStr, parkingTimeStr, carId, ownerId, placeNumber);
    }
    public void updateRecord(int placeNumber, LocalDate drivingDate, LocalTime drivingTime){
        int id = searchByPlaceNumber(placeNumber);
        try{
            PreparedStatement updateRecord = connection.prepareStatement("UPDATE records SET driving_time=?, driving_date=? WHERE id=? AND id_parking=?");
            updateRecord.setString(1, drivingTime.toString());
            updateRecord.setString(2, drivingDate.toString());
            updateRecord.setInt(3, id);
            updateRecord.setInt(4, currentParkingId);
            updateRecord.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public int authorization(String login, String password){
        try{
            PreparedStatement getUserId = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            getUserId.setString(1, login);
            getUserId.setString(2, password);
            ResultSet userIdResult = getUserId.executeQuery();

            //id of selected user
            int userId = 0;
            while (userIdResult.next()){
                userId = userIdResult.getInt("id");
            }

            //id of current parking
            PreparedStatement authorize = connection.prepareStatement("SELECT * FROM parkings WHERE id_user=?");
            authorize.setInt(1, userId);
            ResultSet authorizeResult = authorize.executeQuery();

            int parkingId = 0;
            while (authorizeResult.next()){
                parkingId = authorizeResult.getInt("id");
            }
            return parkingId;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public int registration(String login, String password, int placeQuantity, float placePrice){
        //Get id of user
        int userId = searchByUser(login, password);

        if(userId == 0){
            insertUser(login, password);
            userId = searchByUser(login, password);
        }else{
            JOptionPane.showMessageDialog(null,"You can`t use such login and password!");
            return 0;
        }

        try{
            PreparedStatement register = connection.prepareStatement("INSERT INTO parkings (id_user, volume, price) VALUES (?, ?, ?)");
            register.setInt(1, userId);
            register.setInt(2, placeQuantity);
            register.setFloat(3, placePrice);
            register.executeUpdate();

            int parkingId = searchByParking(userId);
            return parkingId;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public int getVolume(){
        try{
            PreparedStatement getVolume = connection.prepareStatement("SELECT * FROM parkings WHERE id=?");
            getVolume.setInt(1, currentParkingId);
            ResultSet result = getVolume.executeQuery();

            int volume = 0;
            while (result.next()){
                volume = result.getInt("volume");
            }
            return volume;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public float getPrice(){
        try{
            PreparedStatement getPrice = connection.prepareStatement("SELECT * FROM parkings WHERE id=?");
            getPrice.setInt(1, currentParkingId);
            ResultSet result = getPrice.executeQuery();

            float price = 0;
            while (result.next()){
                price = result.getFloat("price");
            }
            return price;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public int getUserId(){
        try{
            PreparedStatement getId = connection.prepareStatement("SELECT * FROM parkings WHERE id=?");
            getId.setInt(1, currentParkingId);
            ResultSet resultForUserId = getId.executeQuery();

            int userId = 0;
            while (resultForUserId.next()){
                userId = resultForUserId.getInt("id_user");
            }
            return userId;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public String getUserLogin(){
        try{
            int userId = getUserId();

            PreparedStatement getLogin = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            getLogin.setInt(1, userId);
            ResultSet resultForUserLogin = getLogin.executeQuery();

            String login = null;
            while (resultForUserLogin.next()){
                login = resultForUserLogin.getString("login");
            }
            return login;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public String getUserPassword(){
        try{
            int userId = getUserId();

            PreparedStatement getPassword = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            getPassword.setInt(1, userId);
            ResultSet resultForUserPassword = getPassword.executeQuery();

            String password = null;
            while (resultForUserPassword.next()){
                password = resultForUserPassword.getString("password");
            }
            return password;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SUPPORTING METHODS
    private int searchByFio(String name, String surname){
        try{
            PreparedStatement preparedStatementForOwner = connection.prepareStatement("SELECT * FROM owners WHERE name=? AND surname=?");
            preparedStatementForOwner.setString(1, name);
            preparedStatementForOwner.setString(2, surname);
            ResultSet result = preparedStatementForOwner.executeQuery();

            //id of searched record
            int id = 0;
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            ex.getMessage();
            return 0;
        }
    }
    private int searchByLicencePlate(String licencePlate){
        try{
            PreparedStatement preparedStatementForOwner = connection.prepareStatement("SELECT * FROM cars WHERE licence_plate=?");
            preparedStatementForOwner.setString(1, licencePlate);
            ResultSet result = preparedStatementForOwner.executeQuery();

            //id of searched record
            int id = 0;
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            ex.getMessage();
            return 0;
        }
    }
    private int searchByPlaceNumber(int placeNumber){
        try{
            PreparedStatement searchRecord = connection.prepareStatement("SELECT * FROM records WHERE place_number=? AND driving_time IS NULL AND driving_date IS NULL");
            searchRecord.setInt(1, placeNumber);
            ResultSet result = searchRecord.executeQuery();
            //Id of searched record
            int id = 0;
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            ex.getMessage();
            return 0;
        }
    }
    private int searchByUser(String login, String password){
        try{
            PreparedStatement preparedStatementForUser = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            preparedStatementForUser.setString(1, login);
            preparedStatementForUser.setString(2, password);
            ResultSet result = preparedStatementForUser.executeQuery();

            //id of searched record
            int id = 0;
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            ex.getMessage();
            return 0;
        }
    }
    private int searchByParking(int userId){
        try{
            PreparedStatement preparedStatementForParking = connection.prepareStatement("SELECT * FROM parkings WHERE id_user=?");
            preparedStatementForParking.setInt(1, userId);
            ResultSet result = preparedStatementForParking.executeQuery();

            //id of searched record
            int id = 0;
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        }catch(SQLException ex){
            ex.getMessage();
            return 0;
        }
    }

    private void insertOwner(String name, String surname){
        try{
            PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO owners (name,surname) VALUES (?,?)");
            insertOwner.setString(1, name);
            insertOwner.setString(2,surname);
            insertOwner.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    private void insertUser(String login, String password){
        try{
            PreparedStatement insertOwner = connection.prepareStatement("INSERT INTO users (login,password) VALUES (?,?)");
            insertOwner.setString(1, login);
            insertOwner.setString(2, password);
            insertOwner.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    private void insertCar(String licencePlate){
        try{
            PreparedStatement insertCar = connection.prepareStatement("INSERT INTO cars (licence_plate) VALUES (?)");
            insertCar.setString(1, licencePlate);
            insertCar.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    public void insertRecord(LocalDate parkingDateStr, LocalTime parkingTimeStr, int carId, int ownerId, int placeNumber){
        try{
            PreparedStatement insertRecord = connection.prepareStatement("INSERT INTO records (parking_time, parking_date, id_car, id_owner, id_parking, place_number) VALUES (?, ?, ?, ?, ?, ?)");
            insertRecord.setString(1, parkingTimeStr.toString());
            insertRecord.setString(2, parkingDateStr.toString());
            insertRecord.setInt(3, carId);
            insertRecord.setInt(4, ownerId);
            insertRecord.setInt(5, currentParkingId);
            insertRecord.setInt(6, placeNumber);
            insertRecord.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }

    private LocalTime StringToLocalTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        return  LocalTime.parse(time, formatter);
    }
    private LocalDate StringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
