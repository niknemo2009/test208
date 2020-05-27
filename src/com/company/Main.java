package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    //130 Способы создания Stream
    public static <T> Stream<T> intoStream(List<T> list){
        return list.stream();
    }
    //138 Посчитать количество слов: слова размером больше Х, которые имеют в позиции У букву Z
    public static void countWordsFile(String fileName ,String symbol, int position, int strLength){
        try(Stream<String> stringStream2 = Files.lines(Paths.get(fileName))){// Читает все строки из файла в виде потока.
            long countWords = stringStream2
                    .flatMap(str -> Stream.of(str.split("[ ,.!?\n]")))
                    .filter(str -> str.length()> strLength && str.contains(symbol))
                    .filter(str -> str.charAt(position) == symbol.charAt(0))
                    .count();
            System.out.println(countWords);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //130
        //набор значений
        System.out.println("_____First type_____");
        Stream<String> streamValues = Stream.of("value1", "value2", "value3");//Создание стрима из значений
        //Применяет функцию к каждому объекту стрима, порядок при параллельном выполнении не гарантируется
        streamValues.forEach(System.out::println);

        // на основе массива
        System.out.println("_____Second type_____");
        String[] stringArray = {"array","a","b","c"};
        Stream<String> stringStream1 = Arrays.stream(stringArray);//Stream.of(stringArray)
        stringStream1.forEach(System.out::println);

        // на основе коллекции
        System.out.println("_____Third type_____");
        // AsList () метод java.util.Arrays класса используется
        // для возврата списка фиксированного размера при поддержке указанного массива
        List<String> stringList = Arrays.asList("one", "two", "three", "four", "five");
        stringList.forEach(System.out::println);

        // на основе файла
        System.out.println("_____Fourth type_____");
        String fileName = "C:\\text\\text.txt";
        // Читает все строки из файла в виде потока.
        try(Stream<String> stringStream = Files.lines(Paths.get(fileName))) {
            stringStream.forEach(System.out::println);
        }  catch (IOException e) {
            e.printStackTrace(); // Метод printStackTrace () в Java - это инструмент, используемый для обработки исключений и ошибок
        }

        // на основе функции
        System.out.println("_____Fifth type_____");
        List<String> names = Arrays.asList("Iryna", "Alina", "Kirill");
        Stream<String> stream = intoStream(names);// function
        stream.forEach(System.out::println);

        //138
        System.out.println("_____Task 138_____");
        countWordsFile(fileName,"a",1,3);

        //149 Посчитать количество дней между датами
        System.out.println("_____Task 149_____");
        LocalDate dateFrom = LocalDate.of(2015, Month.JULY, 12);
        LocalDate dateTo = LocalDate.of(2015, Month.JULY, 15);
        Period intervalPeriod = Period.between(dateFrom, dateTo);
        System.out.println("Difference of days: " + intervalPeriod.getDays());

    }

}