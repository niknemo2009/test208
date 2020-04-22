package lesson2204;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {

    @Test
    void one() {
    }

    @ParameterizedTest
    @MethodSource("sourceSum")
    void testSum(int[] par,int result){
        MathUtil var=new MathUtil();
        int result33=var.sum(par);
        int result2=var.sum(4,2);
        int result1=var.sum();

       assertEquals(result,result33);
//        assertEquals(6,result2);
//        assertEquals(1,result1);

    }
    static Stream<Arguments> sourceSum(){
        return Stream.of(
          Arguments.arguments(new int[]{3,7},0),
          Arguments.arguments(new int[]{2,4,7},6),
          Arguments.arguments(new int[]{},0)
        );
    }

    @ParameterizedTest
    @MethodSource("sourceGetDays")
    void  testConvert(int month,int result){
        MathUtil var=new MathUtil();
        int factRes=var.getDays(month);
        assertEquals(result,factRes);
    }

    static Stream<Arguments> sourceGetDays(){
        return  Stream.of(
          Arguments.arguments(1,31),
          Arguments.arguments(2,28),
          Arguments.arguments(3,31),
          Arguments.arguments(4,30),
          Arguments.arguments(5,31),
          Arguments.arguments(6,30),
          Arguments.arguments(7,31),
          Arguments.arguments(8,31),
          Arguments.arguments(9,30),
          Arguments.arguments(10,31),
          Arguments.arguments(11,30),
          Arguments.arguments(12,31)
        );
    }
}