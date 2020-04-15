package com.parking;

import java.time.LocalDate;
import java.time.LocalTime;

class Record implements Comparable<Record>{
    private int placeNumber;
    private String carLicencePlate;
    private String ownerName;
    private Action action;//поставить enum
    //private Calendar date;//заменить LocalDate
    private LocalDate date;
    private LocalTime time;


    public Record(int placeNumber, String carLicencePlate, String ownerName, Action action, LocalDate date, LocalTime time){
        this.placeNumber = placeNumber;
        this.carLicencePlate = carLicencePlate;
        this.ownerName = ownerName;
        this.action = action;
        this.date = date;
        this.time = time;
    }

    public int getPlaceNumber() { return placeNumber; }
    public Action getAction() {
        return action;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getCarLicencePlate() {
        return carLicencePlate;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() { return time; }

    @Override
    public String toString() {
        return Integer.toString(placeNumber) + ' ' + carLicencePlate + ' ' + ownerName + ' ' + ' ' + action + ' ' + time + ' ' + date;
    }
    @Override
    public int compareTo(Record o) {//для порівняння за власником
        return this.ownerName.compareTo(o.ownerName);
    }
}
