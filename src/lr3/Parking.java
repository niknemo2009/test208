package lr3;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.*;
enum Month {
    DECEMBER("December"),
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November");
    String name;
    Month(String name) {
        this.name = name;
    }
}
class Parking  {

    String namePerson;
    Integer countOfParkingTimes = 0;
    Integer countOfCars;
    String carModel;
    String status;
    static int countOfMonth;
    Month currentMonth;
    double monthPayment;

    public Parking(){}

    public Parking(String namePerson, Integer countOfCars, String carModel,String status) {
        this.namePerson = namePerson;
        this.countOfCars = countOfCars;
        this.carModel = carModel;
        this.status = status;
    }

    public Parking(String namePerson,Integer countOfParkingTimes, String carModel, Month currentMonth,double monthPayment) {
        this.namePerson = namePerson;
        this.countOfParkingTimes = countOfParkingTimes;
        this.carModel = carModel;
        this.currentMonth = currentMonth;
        this.monthPayment = monthPayment;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public String getCarModel() {
        return carModel;
    }

    static ArrayList<Parking>data = new ArrayList<>();
    static {
        data.add(new Parking("John", 2 ,"Jiguli","onParking"));
        data.add(new Parking("Bogdan", 1 ,"Jaguar","outOfParking"));
        data.add(new Parking("Andrew", 2 ,"BMW","outOfParking"));
        data.add(new Parking("Roman", 1 ,"Zubilo","outOfParking"));
    }
    @Override
    public String toString() {
        return "Parking{" +
                "namePerson='" + namePerson + '\'' +
                ", countOfCars=" + countOfCars +
                ", carModel='" + carModel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    public ArrayList<Parking> statusOfCar(String criteria){
        ArrayList<Parking> tempCars = new ArrayList<>();
        for(Parking item: data){
            if(item.status.equals("onParking")){
                tempCars.add(item);
            }
        }
        return tempCars;
    }
    public ArrayList<Parking> parking(int monthCriteria, double cost,Comparator sortCriteria){
        ArrayList<Parking> tempCars = new ArrayList<>();
        for(countOfMonth = 0; countOfMonth <monthCriteria;countOfMonth++) {
            for (Parking item : data) {
                item.currentMonth = Month.values()[countOfMonth];
                item.countOfParkingTimes = (int) ((Math.random() * 30));
                item.monthPayment = 0;
                if(item.countOfParkingTimes>0) {
                    item.monthPayment = cost * item.countOfParkingTimes;
                    tempCars.add(new Parking(item.namePerson,
                            item.countOfParkingTimes,
                            item.carModel,
                            item.currentMonth,
                            item.monthPayment));
                }
            }
        }
        tempCars.sort(sortCriteria);
        return tempCars;
    }
    public Parking report(String type,String criteria) throws InvalidObjectException {
        if(type.equals("auto")){
            for(Parking item: data){
                if(item.carModel.equals(criteria)){
                    return item;
                }
            }
        }
        if(type.equals("person")){
            for(Parking item: data){
                if(item.namePerson.equals(criteria)){
                    return item;
                }
            }
        }
        throw new InvalidObjectException("not found");
    }
}