import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class lr7 {

    //преобразовываем массив в поток целых чисел, затем получаем сумму этого потока методом Stream::sum)
    public int array(int arr[]){
        int total = IntStream.of(arr).sum();
        return total;
    }

    //142
    public void sposob_1(){

        //на основе значений
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        streamFromValues.forEach(st -> System.out.println(st));
        //на основе коллекции
        Collection<String> collection = Arrays.asList("Хочу", "беляш", "с сыром");
        Stream<String> streamFromCollection = collection.stream();
        streamFromCollection.forEach(st -> System.out.println(st));
        //на основе массива
        String[] array = {"a1","a2","a3"};
        Stream<String> streamFromArrays = Arrays.stream(array);
        streamFromArrays.forEach(st -> System.out.println(st));
        //на основе файла
        //Stream<String> streamFromFiles = Files.lines(Paths.get("file.txt"));
    }

    public static void main(String args[])
    {
        lr7 lr = new lr7();
        int array1[] = {1, 2, 11, 22, 13, 10};
        int sum = lr.array(array1);
        System.out.print(sum);
        lr.sposob_1();
    }
}
