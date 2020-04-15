import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PrivateParkingTest {
    PrivateParking parking;
    User user;
    Car car1;
    Car car2;
    LocalDate date;
    LocalTime time;

    public PrivateParkingTest(){
        //Дані, що будуть використані в УСІХ тестах
        user = new User("test", "test");
        parking = new PrivateParking(user, 10, 5.0f);
        car1 = new Car("CA1111BB", "Zinger");
        car2 = new Car("BB2222BB", "Aaron");
        date = LocalDate.of(2020, 2, 12);
        time = LocalTime.of(10, 30, 15);
        parking.ParkCar(car1, 1, date, time);
    }

    @ParameterizedTest
    @DisplayName("method 'ParkCar' testing")
    @CsvSource(value = {"1:false", "2:true", "30:false"}, delimiter = ':')
    void parkCar(int place, boolean expected) {
        boolean actual = parking.ParkCar(car2, place, date, time);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("method 'LeaveParking' test")
    @CsvSource(value = {"1:true", "4:false", "30:false"}, delimiter = ':')
    void leaveParking(int place, boolean expected) {
        boolean actual = parking.LeaveParking(place, date, time);
        assertEquals(expected, actual);
    }
}