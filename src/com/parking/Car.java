package com.parking;


class Car{
    private String licencePlate;
    private String owner;

    public Car(String licencePlate, String owner){
        this.licencePlate = licencePlate;
        this.owner = owner;
    }
    public String getLicencePlate() {
        return licencePlate;
    }
    public String getOwner() {
        return owner;
    }
}
