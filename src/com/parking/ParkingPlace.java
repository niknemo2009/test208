package com.parking;


class ParkingPlace{
    private int number;
    private boolean isVoid = true;
    private Car car;

    public ParkingPlace(int number){
        this.number = number;
    }
    public void AddCar(Car car) {
        this.car = car;
        isVoid = false;
    }
    public void RemoveCar(){
        this.car = null;
        isVoid = true;
    }
    public boolean isVoid() {
        return isVoid;
    }
    public Car getCar() {
        return car;
    }
    public int getNumber() {
        return number;
    }
}