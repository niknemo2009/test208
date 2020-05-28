import ThreadLab.entities.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Program {
    public static void main(String[] args) {

        Program program = new Program();

        // (Завдання 130) Продемонструйте можливі способи створення Stream<String>, а саме –
        //-	на підставі набору значень;
        //-	на підставі масиву
        //-	на підставі колекції
        //-	на підставі файлу
        //-	згенерувати на підставі функції

        // 1 Создание стрима из значений
        Stream<String> streamValues = Stream.of("first", "second", "third", "fourth");
        streamValues.forEach(value -> System.out.print(value + " "));
        System.out.println();

        // 2 Создание стрима из массива
        String[] strings = {"I", "II", "III", "IV"};
        Stream<String> streamArray = Arrays.stream(strings);
        streamArray.forEach(value -> System.out.print(value + " "));
        System.out.println();

        // 3 Создание стрима из коллекции
        List<String> list = Arrays.asList("uno", "dos", "tres", "quatro");
        Stream<String> streamCollection = list.stream();
        streamCollection.forEach(value -> System.out.print(value + " "));
        System.out.println();

        // 4 Создание стрима из файла
        Stream<String> streamFile = null;
        try {
            streamFile = Files.lines(Paths.get("./src/main/resources/data"));
            streamFile.forEach(value -> System.out.print(value + " "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // 5 Создание стрима с помощью функии
            //generate
        Stream<String> streamGenerate = Stream.generate(() -> "str").limit(4);
        streamGenerate.forEach(value -> System.out.print(value + " "));
        System.out.println();

        //-----------------------------------------------------------------------------------------------------------//
        //(Завдання 132) Демонстрація
        try {
            program.findControlStructures("./src/main/java/Test.java");
        }catch (NoSuchFileException ex) {
            System.out.println("\n\nCatched NoSuchFileException " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }catch (IOException ex) {
            System.out.println("Catched Exception " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

        //-----------------------------------------------------------------------------------------------------------//
        // (Завдання 143) Демонстрація
        List<Product> products = Arrays.asList(
                new Product("str", 44f),
                new Product("str", 44f),
                new Product("Hello World!", 44f),
                null,
                null,
                null
        );

        products = (List<Product>) program.modifyCollection(products, product -> {
            if(product != null && product.getName().equals("str")){
                product.setName("NULL");
            }
            return product;
        });
        System.out.println(products);

    }

    // (Завдання 132) Створити метод, який дозволяє відкрити Java-файл та підрахувати в ньому кількість
    // різних керуючих конструкцій(if,for,while,switch) та вивести результати на консоль.
    // Шлях до файлу передається, як параметр методу. Для виконання використати можливості
    // Stream при роботі з файлами та регулярні вирази.
    public void findControlStructures(String path) throws IOException {
        String[] structures = {"if", "for", "while", "switch"};
        for (String string : structures) {
            Stream<String> streamFile = Files.lines(Paths.get(path));
            Pattern pattern = Pattern.compile(string);

            long count = streamFile.
                    flatMap(str -> Arrays.stream(str.split("\\("))).
                    filter(str -> pattern.matcher(str).find()).count();

            System.out.println("Count '" + string + "' = " + count);
        }
    }

    // (Завдання 143) Створіть узагальнений метод, який дозволяє модифікувати будь яку
    //колекцію обєктів певного типу з використанням певної функції.
    //Колекцію та функцію передавати як параметри методу.
    public <T> Collection<T> modifyCollection(Collection<T> collection, Function<T, T> function){
        return collection.stream().map(function).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public <T> Collection<T> modifyCollection(Collection<T> collection, Consumer<T> function){
        return collection.stream().peek(function).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
