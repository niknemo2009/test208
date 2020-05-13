package lesson1305;

import Lesson0505.Example2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Example3 {

    public static void main(String[] args) {
       String[] arr={"qwe","qaz","zxc"};
       Stream<String> str1= Arrays.stream(arr);
       Stream<String> str2=Stream.of(arr);
       Stream<String> str3=Stream.of("qwe","dfdf");
        List<String> list=new ArrayList<>();
        list.add("qwew");
        list.add("qwew33");
        list.add("qwew33");
Stream<String> str4=list.stream();
//str4=Stream.builder().add("sasass").add("qwwqw").build();
   // 3 символ у
   // ті які починаються з великої букви
   // ті які відповідають рег вир [a-z0-9]{3,5}
        Predicate<String>  pr1=x-> x.charAt(3)=='у';
        Predicate<String>  pr2=x-> x.substring(0,1).toUpperCase().equals(x.substring(0,1));
        Predicate<String>  pr3=x-> x.matches("[a-z0-9]{3,5}");
        Predicate<String> condition2 = (String str) -> Character.isUpperCase(str.charAt(0));
        Predicate<String> condition3 =  str -> str.matches("[A-Z]([a-zA-Z]*)]");

        System.out.println(str1.filter(pr1.negate()).count());

        System.out.println("Qwe".matches("[A-Z]([a-zA-Z]*)"));
        System.out.println("Яwe".matches("^[A-ZА-Я]"));
        // виводити на консоль всі елементи конвертуючі всі букви у верхній регистр
        // виводити на консоль тіки останні ви елементів
        // виводити всі елементи йцу  уцй
        Consumer<String> con1=x-> System.out.println(x.toUpperCase());
        Consumer<String> con2=x-> System.out.println(String.valueOf(x.charAt(x.length()-1)));
        Consumer<String> con3=Example3::reverse ;
        str1.forEach(con3);
        String [] arr1 = {"Java", "Python", "Data"};
        Arrays.stream(arr1).forEach(con3);


    }
static void  reverse(String row){Function<String, Integer> fun = r -> {
    int coun = 0;
    String[] temp = r.split("");
    for (String letter : temp) {
        if (letter.equals(letter.toUpperCase())) {
            coun++;
        }
    }
    return r.length() - coun;
};
        char[]  arr=row.toCharArray();
        for(int i=arr.length-1;i>=0;i--){
            System.out.print(arr[i]);
        }
}
}


