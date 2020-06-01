package lr3;
import java.io.InvalidObjectException;
import java.util.*;

public class Laba3 {
    public static void main(String[] args) {
        Parking p1 = new Parking();
        try {
            p1 = p1.report("person", "John");
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        System.out.println(p1);
        NameComparator nameComparator = new NameComparator();
        CarsComparator carsComparator = new CarsComparator();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        ArrayList<Parking> statusOfCar;
        statusOfCar = p1.statusOfCar("onParking");
        for (Parking item : statusOfCar) {
            System.out.println(item);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        ArrayList<Parking> statusOfParking;
        statusOfParking = p1.parking(1, 5, nameComparator);
        for (Parking item : statusOfParking) {
            System.out.println(
                    "Month: "
                            + item.currentMonth
                            + "\n" + "count of parking of month: "
                            + item.carModel + ": "
                            + item.countOfParkingTimes + "\n"
                            + item.namePerson + ": cost of month is " + item.monthPayment + "$\n");
        }
    }

}

