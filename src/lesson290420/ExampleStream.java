package lesson290420;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ExampleStream {
    public static void main(String[] args) {
         List<String> list=new Vector<>();
         Predicate<Integer>  lambda=  (Integer opa)-> {
             boolean result=opa>3;
             if(result==true){
                 System.out.println(opa);
             }

             return result;
         };
            // public boolean test(Integer integer) {
            // сігнатура методу=імя методу+ кількість параметрів + тип параметрів+ порядок параметрів
         list.add("one");
         list.add("two");
         list.add("three");
         list.add("for");
         list.add("five");
//         list.stream().filter(new Predicate<String>() {
//             @Override
//             public boolean test(String s) {
//                 return true;
//             }
//         }).forEach(new Consumer<String>() {
//             @Override
//             public void accept(String s) {
//                 System.out.println(s);
//             }
//         });
//         ExampleStream var=new ExampleStream();
         list.stream().filter((String w)->{

             return w.startsWith("o");
         }).forEach(r-> System.out.println(r));
//         list.stream().filter(w->true).forEach(System.out::println);
//         list.stream().filter(w->true).forEach(var::printOrdinaru);

       // ((Vector)list).add(23);
    }

   static void print(String q){
        System.out.println(q);
    }
    void printOrdinaru(String row){
        System.out.println(row);
    }

    void  one(int par2,String par){

    }
    int  one(String par33,int par22){
return 3;
    }
}
