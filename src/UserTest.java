import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    PrivateParking parking;
    User user;

    public UserTest(){
        //Дані, що будуть використані в УСІХ тестах
        user = new User("test", "test");
        parking = new PrivateParking(user, 10, 5.0f);
    }

    @ParameterizedTest
    @DisplayName("method 'LogIn' testing")
    @CsvSource(value = {"test:true", "user:false"}, delimiter = ':')
    void logIn(String login, boolean expected) {
        boolean actual = this.user.LogIn(login, "test");
        assertEquals(expected, actual);
    }
}