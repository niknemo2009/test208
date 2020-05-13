package com.Lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Варіант №5
/*Завдання №134
Змоделювати склад улюблених книжок. Для генерації наявних книжок на складі використати Stream.
Книжки мають слідуючі характеристики- автор, назва, кількість сторінок, тираж, ціна.
Кількість сторінок,тираж,ціна генеруються випадково.
Назва та автор відповідають вашим уподобанням.
Загальна кількість книжок на складі не повинна перевищувати 50000, але повинна бути більшою 10000.
        Модель повинна мати слідуючи можливості:
        -	мати метод, який дозволяє знайти книги з мінімальною та максимальною ціною
        -	мати метод, який повертає потік,який складається з усіх книг певного автору
        -	мати метод, який сортує книги по будь-якому атрибуту книг та виводить їх у список*/
class Book{
    private String author;
    private String name;
    private int pages;
    private int quantity;
    private float price;

    public Book(){
        String[] authors = {"Роберт Мартин", "Герберт Шилдт", "Брюс Эккель"};
        String[] bookNames = {"Философия Java", "Чистый код", "Java.Полное руководство"};

        this.author = authors[(int)(Math.random()* authors.length)];
        this.name = bookNames[(int)(Math.random()* bookNames.length)];
        this.pages = (int)(Math.random()*800);
        this.quantity = (int)(Math.random()*300);
        this.price = (float)(Math.random()*1000);
    }

    public String getAuthor(){
        return this.author;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "'" + this.name
                    + "' by " + this.author
                    + " pages: " + this.pages
                    + " quantity: " + this.quantity
                    + " price: " + this.price;
    }
}
enum sortOptions{
    AUTHOR,
    NAME,
    PAGES,
    QUANTITY,
    PRICE
}
class Library{
    public List<Book> generateBooks(int booksQuantity){
        return Stream.generate(Book::new).limit(booksQuantity).collect(Collectors.toList());
    }
    public Stream<Book> filterByAuthor(List<Book> books, String author){
        return books.stream().filter(book -> book.getAuthor().equals(author));
    }
    public Stream<Book> sort(List<Book> books, sortOptions options){
        switch (options){
            case AUTHOR:
                return books.stream().sorted(Comparator.comparing(Book::getAuthor));
            case NAME:
                return books.stream().sorted(Comparator.comparing(Book::getName));
            case PAGES:
                return books.stream().sorted(Comparator.comparing(Book::getPages));
            case PRICE:
                return books.stream().sorted(Comparator.comparing(Book::getPrice));
            case QUANTITY:
                return books.stream().sorted(Comparator.comparing(Book::getQuantity));
            default:
                return null;
        }
    }
    public Book bookWithMaxPrice(List<Book> books){
        return books.stream().max(Comparator.comparing(Book::getPrice)).get();
    }
    public Book bookWithMinPrice(List<Book> books){
        return books.stream().min(Comparator.comparing(Book::getPrice)).get();
    }
}

/*Завдання №145
Розробіть  свою узагальнену колекцію типу Set. В якості структури в якій будете зберігати дані оберіть масив.
*/
class mySet<T>{
    T [] data;

    public mySet(){
        data = (T[]) new Object[0];
    }

    public void add(T newElement){
        long count = Arrays.stream(data).filter(presentElement -> {
            if(presentElement != null){
                return presentElement.equals(newElement);
            }else{
                return false;
            }
        }).count();
        if(count == 0 && data.length > 0){
            //Скільки заповнених комірок в масиві
            int filledItems = 0;
            for(int i = 0; i < data.length; i++){
                if(data[i] != null){
                    filledItems++;
                }
            }
            int oldLength = data.length;

            //Виділення пам'яті, якщо масив заповнено
            T[] newData;
            if(filledItems == oldLength - (int)(oldLength * 0.4)){
                newData = (T[]) new Object[oldLength * 2];
            }else {
                newData = (T[]) new Object[oldLength];
            }
            System.arraycopy(data, 0, newData, 0, oldLength);

            //Оновлення даних
            newData[filledItems] = newElement;
            data = newData;
        }else if(count == 0  && data.length == 0) {
            T[] newData = (T[]) new Object[1];
            newData[0] = newElement;
            data = newData;
        }
    }
    public void addAll(Collection<T> collection){
        for(T element : collection){
            this.add(element);
        }
    }
    public void remove(T element){
        int indexOfIgnored = -1;
        for(int i = 0; i < data.length; i++){
            if(data[i].equals(element)){
                indexOfIgnored = i;
                break;
            }
        }
        if(indexOfIgnored != -1){
            T[] newData = (T[]) new Object[data.length];
            for(int i = 0; i < data.length - 1; i++){
                if(i < indexOfIgnored ){
                    newData[i] = data[i];
                }else{
                    newData[i] = data[i + 1];
                }
            }
            data = newData;
        }
    }
    public void removeAll(Collection<T> collection){
        for(T element : collection){
            this.remove(element);
        }
    }
    public void retainAll(Collection<T> collection){
        for (T item : data) {
            if (item != null && !collection.contains(item)) {
                this.remove(item);
            }
        }
    }
    public int size(){
        int filledItems = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i] != null){
                filledItems++;
            }
        }
        return filledItems;
    }
    public boolean isEmpty(){
        return data.length == 0;
    }
    public boolean contains(T element){
        long count = Arrays.stream(data).filter(presentElement -> {
            if(presentElement != null){
                return presentElement.equals(element);
            }else{
                return false;
            }
        }).count();

        return count != 0;
    }
    public void clear(){
        data = (T[]) new Object[0];
    }
    public T[] toArray(){
        int filledItems = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i] != null){
                filledItems++;
            }
        }

        return this.data;
    }
    public Stream<T> stream(){
        return Arrays.stream(data);
    }

    @Override
    public String toString() {
        return Arrays.stream(data).filter(Objects::nonNull).collect(Collectors.toList()).toString();
    }
}
public class Program {
    public static void main(String[] args) {
        //*Завдання №134
        System.out.println("Stream");
        Library lib = new Library();
        //Можна задати будь-яку кількість книг, для перевірки зручно створити малу кількість
        List<Book> books = lib.generateBooks(5);
        for (Book book:books) {
            System.out.println(book);
        }
        System.out.println("------------------------");
        lib.filterByAuthor(books, "Брюс Эккель").forEach(System.out::println);
        System.out.println("------------------------");
        lib.sort(books, sortOptions.PAGES).forEach(System.out::println);
        System.out.println("-------------------------");
        System.out.println(lib.bookWithMaxPrice(books));
        System.out.println("-------------------------");
        System.out.println(lib.bookWithMinPrice(books));

        /*Завдання №130:
        Продемонструйте можливі способи створення Stream<String>, а саме –
        -	на підставі набору значень;*/
        Stream.of("cat", "kitten", "horse", "dog", "bird").forEach(System.out::println);

        //-	на підставі масиву
        String [] strings = {"cat", "kitten", "horse", "dog", "bird"};
        Arrays.stream(strings).forEach(System.out::println);

        //-	на підставі колекції
        List<String> list = List.of("cat", "kitten", "horse", "dog", "bird");
        list.stream().forEach(System.out::println);

        //-	на підставі файлу
        try(Stream<String> stream = Files.lines(Paths.get("c://strings.txt"))){
            stream.forEach(System.out::println);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        //-	згенерувати на підставі функції
        char [] chars = {'j', 'a', 'v', 'a'};
        Stream.generate(() -> new String(chars)).limit(3).forEach(System.out::println);

        //Завдання №145
        mySet<Integer> set = new mySet<>();
        //System.out.println(set.isEmpty());
        set.add(1);
        //System.out.println(set);
        set.add(1);
        //System.out.println(set);
        List<Integer> numbers = List.of(1, 3, 3, 4, 5, 6, 7, 8);
        set.addAll(numbers);
        //System.out.println(set);
        set.remove(3);
        //System.out.println(set);
        List<Integer> numbersToRemove = List.of(7, 8);
        set.removeAll(numbersToRemove);
        //System.out.println(set);
        //System.out.println(set.contains(4));
        //System.out.println(set.size());
        List<Integer> numbersToRetain = List.of(4, 5, 6);
        set.retainAll(numbersToRetain);
        //System.out.println(set);
        set.clear();
        //System.out.println(set);
    }
}
