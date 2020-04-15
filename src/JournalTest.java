import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    PrivateParking parking;
    User user;

    Car car1;
    Car car2;

    LocalDate date;

    LocalTime time1;
    LocalTime time2;

    public JournalTest(){
        //Дані, що будуть використані в УСІХ тестах
        user = new User("test", "test");
        parking = new PrivateParking(user, 10, 5.0f);

        car1 = new Car("CA1111BB", "Zinger");
        car2 = new Car("BB2222BB", "Aaron");

        date = LocalDate.of(2020, 2, 12);

        time1 = LocalTime.of(10, 30, 15);
        time2 = LocalTime.of(12,15,25);

        parking.ParkCar(car1, 1, date, time1);
        parking.LeaveParking(1, date, time2);
    }

    @ParameterizedTest
    @DisplayName("method 'searchByOwner' testing")
    @ValueSource(strings = {"Zinger", "WWW"})
    void searchByOwner(String name) {
        boolean actual = !parking.getJournal().SearchByOwner(name).isEmpty();
        boolean expected = name.equals("Zinger");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("method 'CreateOrder' testing")
    @MethodSource("provideValuesForCreateOrder")
    void createOrder(String owner, float price, LocalDate start, LocalDate end, double expected) {
        double actual = parking.getJournal().CreateOrder(owner, price, start, end);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideValuesForCreateOrder(){
        return Stream.of(
                //Неіснуючий користувач
                Arguments.of(
                        "WWW",
                        5.0f,
                        LocalDate.of(2020, 2, 12),
                        LocalDate.of(2020, 2, 15),
                        0),
                //Від'ємна вартість
                Arguments.of(
                        "Zinger",
                        -6.0f,
                        LocalDate.of(2020, 2, 12),
                        LocalDate.of(2020, 2, 15),
                        0),
                //Припаркувалися пізніше, ніж поїхали з парковки
                Arguments.of("Zinger",
                        5.0f,
                        LocalDate.of(2020, 2, 12),
                        LocalDate.of(2020, 2, 4),
                        0),
                //Звіт за період, за який не було паркувань
                Arguments.of("Zinger",
                        5.0f,
                        LocalDate.of(2020, 3, 12),
                        LocalDate.of(2020, 3, 16),
                        0),
                //Чи рахує, коли час паркування та виїзду співпадає зі start?
                Arguments.of("Zinger",
                        5.0f,
                        LocalDate.of(2020, 2, 12),
                        LocalDate.of(2020, 2, 16),
                        8.75),
                //Чи рахує, коли час паркування та виїзду співпадає зі end?
                Arguments.of("Zinger",
                        5.0f,
                        LocalDate.of(2020, 2, 11),
                        LocalDate.of(2020, 2, 12),
                        8.75)
        );
    }
}