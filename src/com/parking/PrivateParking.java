package com.parking;


import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PrivateParking {
    private int volume;//кількість місць на парковці
    private float priceOfPlace;//ціна одного місця на парковці
    private ArrayList<ParkingPlace> places;//колекція, що містить всі місця для паркування
    private Journal journal;//журнал записів про всі машини, що паркувалися або виїхали з парковки
    private User keeper;//власник паркування

    public PrivateParking(User keeper, int volume, float priceOfPlace) {
        this.keeper = keeper;
        this.volume = volume;
        this.places = new ArrayList<ParkingPlace>();
        this.priceOfPlace = priceOfPlace;
        this.journal = new Journal();
        //Композиція
        for (int i = 0; i < volume; i++) {
            this.places.add(new ParkingPlace(i + 1));
        }
    }

    public int getVolume() {
        return volume;
    }
    public float getPriceOfPlace() {
        return priceOfPlace;
    }
    public User getKeeper() { return keeper; }

    //Методи, що дозволяють змінювати кількість машин на парковці та отримувати дані про ці машини
    public void ParkCar(Car car, int placeForParking, LocalDate date, LocalTime time){
        if(placeForParking > volume) {
            JOptionPane.showMessageDialog(null, "Даного місця на парковці не існує!");
        }else{
            if(!places.get(placeForParking - 1).isVoid()) {
                JOptionPane.showMessageDialog(null, "Дане місце зайняте іншим автомобілем!");
            }
            if(places.get(placeForParking - 1).isVoid()){//placeForParking - 1, бо індексація йде с нуля,  нумерація - з 1
                places.get(placeForParking - 1).AddCar(car);
                //Робимо запис про паркування автомобіля в журналі
                this.journal.AddRecord(placeForParking, car.getLicencePlate(), car.getOwner(), Action.PARKING, date, time);
            }
        }
    }
    public void LeaveParking(int placeForParking, LocalDate date, LocalTime time){
        if(placeForParking > volume) {
            JOptionPane.showMessageDialog(null, "Даного місця на парковці не існує!");
        }else{
            if(places.get(placeForParking - 1).isVoid()) {
                JOptionPane.showMessageDialog(null, "Дане місце вже вільне!");
            }
            if(!places.get(placeForParking - 1).isVoid()) {
                Car currentCar = places.get(placeForParking - 1).getCar();//отримати дані про машину, доки її не видалено
                places.get(placeForParking - 1).RemoveCar();
                //Робимо запис про те, що автомобіль залишив парковку
                this.journal.AddRecord(placeForParking, currentCar.getLicencePlate(), currentCar.getOwner(), Action.MOVING, date, time);
            }
        }
    }
    //Всі необхідні методи пошуку, сортування та формування звітів знаходяться у журналі
    public Journal getJournal() {
        return journal;
    }
}
