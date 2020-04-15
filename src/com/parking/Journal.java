package com.parking;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*Для зберігання даних про паркування на стоянці - об'єкти класів Car, ParkingPlace, PrivateParking містять дані про поточний
стан парковки, але не містять історію.
* */
public class Journal{
    private ArrayList<Record> records;//масив записів про всі автомобілі, що паркувалися та залишали парковку

    public Journal(){
        this.records = new ArrayList<>();
    }
    public void AddRecord(int placeNumber, String carLicencePlate, String ownerName, Action action, LocalDate date, LocalTime time){
        records.add(new Record(placeNumber, carLicencePlate, ownerName, action, date, time));
    }
    //ВСІ ЗАПИСИ, що зареєстровані в журналі
    public ArrayList<Record> AllRecords(){
        return records;
    }
    public void setRecords(ArrayList<Record> newRecords){
        this.records = newRecords;
    }

    //СОРТУВАННЯ
    public void sortByOwner(){//за власником
        Collections.sort(records);
    }
    public void sortByCar(){//за авто
        records.sort(new Comparator<Record>() {//для порівняння за автомобілем
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getCarLicencePlate().compareTo(o2.getCarLicencePlate());
            }
        });
    }

    //ПОШУК
    public ArrayList<Record> SearchByOwner(String owner){//за власником
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if (isEqualTo(record.getOwnerName(), owner)){
                validRecords.add(record);
            }
        }
        return validRecords;
    }
    public ArrayList<Record> SearchByLicencePlate(String licencePlate){//за авто
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if(isEqualTo(record.getCarLicencePlate(), licencePlate)){
                validRecords.add(record);
            }
        }
        return validRecords;
    }
    public ArrayList<Record> RecordsInRange(LocalDate min, LocalDate max){//за періодом часу
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if((record.getDate().compareTo(min) > 0) && (record.getDate().compareTo(max) < 0)) {
                validRecords.add(record);
            }
        }
        return validRecords;
    }

    //СТАН ПАРКОВКИ НА ДАНИЙ МОМЕНТ
    public ArrayList<Record> NowOnParking(){
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять

        String car;//перевіряємо паркування саме за автомобілем, бо у однієї людини може бути декілька машин
        int timesOfParking = 0;//скільки разів приїхав на парковку
        int timesOfMoving = 0;//скільки разів поїхав з парковки

        for(int i = 0; i < records.size(); i++){
            //Для якого автомобіля перевіряємо
            car = records.get(i).getCarLicencePlate();
            for (Record record : records) {
                if (record.getCarLicencePlate().equals(car) && record.getAction().equals(Action.PARKING)) {
                    timesOfParking++;
                }
                if (record.getCarLicencePlate().equals(car) && record.getAction().equals(Action.MOVING)) {
                    timesOfMoving++;
                }
            }
            //Якщо машина зараз на парковці, то вона більше разів паркувалася, ніж їхала з парковки
            if(timesOfParking > timesOfMoving){
                validRecords.add(records.get(i));
            }
            timesOfMoving = 0;
            timesOfParking = 0;
        }

        return validRecords;
    }

    //ФОРМУВАННЯ ЗВІТУ
    public void CreateOrder(String owner, float price, LocalDate start, LocalDate end){
        LocalTime parkingTime = null;//час, коли запаркувався
        LocalDate parkingDate = null;//день, коли запаркувався

        LocalTime drivingTime = null;//час, коли поїхав
        LocalDate drivingDate = null;//день, коли поїхав

        double totalTimeOnParking = 0;
        double totalSum = 0;
        String message = "";

        boolean isInRange = false;
        boolean isFound = false;

        for(Record record : records){
            //Час, коли запаркувався
            if(record.getOwnerName().equals(owner) && record.getAction().equals(Action.PARKING)){
                parkingTime = record.getTime();
                parkingDate = record.getDate();
            }

            //Час, коли поїхав с парковки
            if(record.getOwnerName().equals(owner) && record.getAction().equals(Action.MOVING)){
                drivingTime = record.getTime();
                drivingDate = record.getDate();
            }


            //Чи знайдено записи про від'їзд та паркування
            isFound = parkingTime != null && drivingTime != null;

            if(isFound){
                //Чи знаходяться ці записи в заданому діапазоні
                isInRange = (parkingDate.isAfter(start) || parkingDate.equals(start)) && (drivingDate.isBefore(end) || drivingDate.equals(end));

                //Записи в заданому діпазоні і машина виїхала та повернулася в один день
                if(isInRange && parkingDate.equals(drivingDate)){//
                        double quantityOfHours = drivingTime.getHour() - parkingTime.getHour();
                        double quantityOfMinutes = drivingTime.getMinute() / 60.00 - parkingTime.getMinute() / 60.00;

                        totalTimeOnParking +=  quantityOfHours + quantityOfMinutes;
                        totalSum += totalTimeOnParking * price;

                        String formattedTotalTimeOnParking = String.format("%.2f", totalTimeOnParking);
                        String formattedTotalSum = String.format("%.2f", totalSum);
                        message = owner + "`s car was being on parking for " + formattedTotalTimeOnParking + " hours. Total sum: " + formattedTotalSum + "$";

                        JOptionPane.showMessageDialog(null, message);

                        //Повернення до початкових налаштувань
                        parkingTime = null;
                        parkingDate = null;

                        drivingTime = null;
                        drivingDate = null;
                }

                //Записи в заданому діпазоні і машина була на парковці кілька днів
                if(drivingDate != null){
                    if(isInRange && !parkingDate.equals(drivingDate)){
                        Period period = Period.between(parkingDate, drivingDate);
                        int quantityOfDays = period.getDays();
                        double quantityOfHours = (24 - parkingTime.getHour()) + drivingTime.getHour();
                        double quantityOfMinutes = drivingTime.getMinute() / 60.00 - parkingTime.getMinute() / 60.00;

                        totalTimeOnParking +=  quantityOfDays * 24 + quantityOfHours + quantityOfMinutes;
                        totalSum += totalTimeOnParking * price;

                        String formattedTotalTimeOnParking = String.format("%.2f", totalTimeOnParking);
                        String formattedTotalSum = String.format("%.2f", totalSum);
                        message = owner + "`s car was being on parking for " + formattedTotalTimeOnParking + " hours. Total sum: " + formattedTotalSum + "$";

                        JOptionPane.showMessageDialog(null, message);

                        //Повернення до початкових налаштувань
                        parkingTime = null;
                        parkingDate = null;

                        drivingTime = null;
                        drivingDate = null;
                    }
                }
            }
        }
    }
    //ДОПОМІЖНІ МЕТОДИ
    private boolean isEqualTo(String str1, String str2){//пошук, який не залежить від регістра
        return str1.toLowerCase().equals(str2.toLowerCase());
    }
}

