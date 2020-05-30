import Auth.Authorization;
import Model.Data.Model;
import Model.FileModelInternetShop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestAuthorization {
    private Model model;
    private Authorization auth;

    public TestAuthorization() {
        this.model = new FileModelInternetShop();
        this.auth = new Authorization(model);
    }

    @DisplayName("test 'signIn'")
    @ParameterizedTest
    @CsvSource(value = {"seller1@gmail.com;password1;true", "seller2@gmail.com;password;false",
                        "customer1@gmail.com;qwertyui;false", "customer2@gmail.com;password5;true",
                        "test@test.com;12345678;true"}, delimiter = ';')
    public void testSignIn(String login, String password, boolean expected){
        boolean actual = auth.singIn(login,password);
        assertEquals(expected, actual);
    }
}
