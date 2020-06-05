package lr7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        //140
        Stream<Integer> integerStream = Stream.of(1,12,1235,1,131);
        integerStream.filter(x -> x<200).sorted().limit(2).forEach(System.out::println);
        LocalDate currentTime =  LocalDate.of(1970, 1, 1);
        Stream<LocalDate> timeGenerator = Stream.iterate(currentTime, x -> x.plusDays(1))
                .filter(x -> ((x.getYear()%100)/10) % 3 == 0)
                .limit(5);
        Stream<String> stringStream = timeGenerator.map(LocalDate::toString);
        stringStream.forEach(System.out::println);
        //130
        //на подставе набора значений
        Stream<String> stringStream0 = Stream.of("navi","virtus pro","syman");
        stringStream0.forEach(System.out::println);
        //на подставе массивов
        String arr[] = {"navi","virtus pro","syman"};
        Stream<String> stringStream1 = Arrays.stream(arr);
        stringStream1.forEach(System.out::println);
        //на подставе коллекций
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("navi");
        arrayList.add("virtus pro");
        arrayList.add("syman");
        Stream<String> stringStream2 = arrayList.stream();
        stringStream2.forEach(System.out::println);
        //на подставе файла
        Stream<String> stringStream3 = Files.lines(Paths.get("C:\\Users\\pavel\\IdeaProjects\\Laba3\\src\\lr7\\TestExForJava.txt"));
        stringStream3.forEach(System.out::println);
        //сгенерировать на подставе функции
        Stream<String> stringStream4 = Stream.generate(() -> "Navi davi").limit(3);
        stringStream4.forEach(System.out::println);
    }
}
