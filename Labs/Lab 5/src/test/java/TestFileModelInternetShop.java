import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.Data.Model;
import Model.FileModelInternetShop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileModelInternetShop {
    private Model model;
    private ArrayList<Order> ordersData;

    public TestFileModelInternetShop(){
        this.model = new FileModelInternetShop();
        this.ordersData = new ArrayList<>();

        Order order1 = new Order(3);
        order1.addProduct(new Product("computer", "Asus", 31500f), 5);
        order1.addProduct(new Product("computer", "Aser", 13500f), 5);
        order1.addProduct(new Product("keyboard", "HyperX", 900f), 5);

        Order order2 = new Order(4);
        order2.addProduct(new Product("headphones", "iPhone", 190f), 25);
        order2.addProduct(new Product("telephone", "iPhone", 22000f), 15);
        order2.addProduct(new Product("tablet", "Xiaomi", 900f), 51);

        ordersData.add(order1);
        ordersData.add(order2);

        ordersData.forEach(order -> model.addOrder(order));
    }

    @DisplayName("test 'findByName'")
    @ParameterizedTest
    @ValueSource(strings = {"teL", "qqqqqqqq"})
    public void testFindByName(String name){
       boolean actual = !model.findByName(name).isEmpty();
       boolean expected = name.equals("teL");
       assertEquals(expected, actual);
    }

    @DisplayName("test 'completeOrder'")
    @ParameterizedTest
    @CsvSource(value = {"1;true", "2;false", "3;false", "4;false", "5;false"}, delimiter = ';')
    public void testCompleteOrder(Long id, boolean expected){
        boolean actual = model.completeOrder(id);
        assertEquals(expected, actual);
    }

    @DisplayName("test 'checkProduct'")
    @ParameterizedTest
    @MethodSource("provideDataForTest")
    public void testCheckProduct(Product product, int count, boolean expected) {
        boolean actual = model.checkProduct(product, count);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetManufacturers(){
        int actual = model.getManufacturers("computer").size();
        assertEquals(3, actual);

        actual = model.getManufacturers("telephone").size();
        assertEquals(5, actual);

        actual = model.getManufacturers("tablet").size();
        assertEquals(3, actual);

        actual = model.getManufacturers("keyboard").size();
        assertEquals(1, actual);

        actual = model.getManufacturers("speakers").size();
        assertEquals(1, actual);

        actual = model.getManufacturers("headphones").size();
        assertEquals(4, actual);

        actual = model.getManufacturers("vacuum cleaner").size();
        assertEquals(0, actual);
    }

    private static Stream<Arguments> provideDataForTest(){
        return Stream.of(
                //Больше чем есть на складе
                Arguments.of(
                        new Product("headphones","iPhone",190),
                        2000,
                        false
                ),
                //null значение
                Arguments.of(
                        null,
                        100,
                        false
                ),
                //Несуществующй товар
                Arguments.of(
                        new Product("vacuum cleaner","Sony",11190),
                        1,
                        false
                ),
                Arguments.of(
                        new Product("computer", "Asus", 31500),
                        10,
                        true
                )
        );
    }
}
