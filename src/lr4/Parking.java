package lr4;
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
    public Parking(Month currentMonth,String carModel, Integer countOfParkingTimes, String namePerson,double monthPayment) {
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
        data.add(new Parking("Roman", 1 ,"Zubilo","onParking"));
    }
    static ArrayList<Parking> info = new ArrayList<>();

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
    public void parking(int monthCriteria, double cost){
        for(countOfMonth = 0; countOfMonth <monthCriteria;countOfMonth++) {
            for (Parking item : data) {
                item.currentMonth = Month.values()[countOfMonth];
                item.countOfParkingTimes = (int) ((Math.random() * 30));
                item.monthPayment = 0;
                if(item.countOfParkingTimes>0) {
                    item.monthPayment = cost * item.countOfParkingTimes;
                    info.add(new Parking(item.currentMonth,
                            item.carModel,
                            item.countOfParkingTimes,
                            item.namePerson,
                            item.monthPayment));
                }
            }
        }
    }
    public ArrayList<Parking> sortParking(int monthCriteria,Comparator<Parking> comparator){
        ArrayList<Parking> arrayList1 = new ArrayList<>();

            arrayList1.addAll(info);

        arrayList1.sort(comparator);
        return arrayList1;
    }
    public ArrayList<Parking> report(String type,String criteria) {
        ArrayList<Parking> reportParking = new ArrayList<>();
        if(type.equals("auto")){
            for(Parking item: info){
                if(item.carModel.equals(criteria)){
                    reportParking.add(item);
                }
            }
        }
        if(type.equals("person")){
            for(Parking item: info){
                if(item.namePerson.equals(criteria)){
                    reportParking.add(item);
                }
            }
        }
        return reportParking;
    }
}