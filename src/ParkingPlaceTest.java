import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceTest {
    PrivateParking parking;
    User user;
    Car car;
    LocalDate date;
    LocalTime time;

    public ParkingPlaceTest(){
        //Дані, що будуть використані в УСІХ тестах
        user = new User("test", "test");
        parking = new PrivateParking(user, 10, 5.0f);
        car = new Car("CA1111BB", "Zinger");
        date = LocalDate.of(2020, 2, 12);
        time = LocalTime.of(10, 30, 15);
        parking.ParkCar(car, 1, date, time);
    }

    @Test
    void isVoid() {
        //Для зайнятого місця
        boolean actual = parking.getParkingPlace(1).isVoid();
        assertFalse(actual);

        //Для пустого місця
        actual = parking.getParkingPlace(3).isVoid();
        assertTrue(actual);
    }
}