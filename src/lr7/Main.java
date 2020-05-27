package lr7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;


public class Main {

    static <T extends Comparable<? super T>> List mySort(T[]arr){
        return Stream.of(arr).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        Integer[] arr = {1, 2, 3, 0, -1, 5};
        String[] strings = {"gg", "AaaA", "FFF", "zzz"};

        var list = mySort(strings);
        list.forEach(System.out::println);

        LocalDate currentTime =  LocalDate.of(1970, 1, 1);
        System.out.println(currentTime.getYear() % 100 / 10);
        Stream<LocalDate> timeGenerator = Stream.iterate(currentTime, x -> x.plusDays(1))
                .filter(x -> ((x.getYear()%100)/10) % 3 == 0)
                .limit(10000);
        Stream<String> stringStream = timeGenerator.map(LocalDate::toString);
        stringStream.forEach(System.out::println);

        //на підставі набору значень
        Stream<String> stingFromStream = Stream.of(
                "Hello",
                "Andrew",
                "John",
                "world"
        );
        stingFromStream.forEach(System.out::println);

        //на підставі масиву

        String []stringArray = {"hh", "gg", "bb"};
        Stream<String> arrayFromStream = Arrays.stream(stringArray);
        arrayFromStream.forEach(System.out::println);

        //на підставі колекції

        Collection<String> stringCollection = Arrays.asList("bb", "aa", "nn");
        Stream<String> collectionFromStream = stringCollection.stream();
        collectionFromStream.forEach(System.out::println);

        //на підставі файлу

        Stream<String> fileFromStream = Files
                .lines(Paths.get("C:\\Users\\direc\\Desktop\\Java\\labs\\src\\lr7\\streamTest.txt"));
        fileFromStream.forEach(System.out::println);

        //згенерувати на підставі функці

        Stream<String> functionFromStream = Stream.generate(() -> "gen text")
                .limit(5);

    }
}


