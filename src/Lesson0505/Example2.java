package Lesson0505;

import java.time.LocalDate;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Example2 {

    public static void main(String[] args) {
int[] arr={1,33,23,22};
  Example2 ex2=new Example2();
//  int[] result=ex2.filtrArray(arr,r->r%2==0);
//        System.out.println(Arrays.toString(result));
        //****************************************************
        String arr1[]={"qwe","wsx","qazwsx"};
        System.out.println(ex2.filtrRefer(arr1,w->w.length()>45));
        LocalDate[] dates={LocalDate.of(2020,12,22),
                LocalDate.of(2020,12,27)};
        LocalDate[] result=ex2.filtrRefer33(dates,r->r.isAfter(LocalDate.of(2020,12,23)));
        System.out.println(Arrays.toString(result));

    }
    <T> Collection<T> filtrRefer(T[] arr, Predicate<T> condition){

        return Arrays.stream(arr).filter(condition).collect(Collectors.toList());
    }

    <T> T[] filtrRefer33(T[] arr, Predicate<T> condition){

List<T> tempList=Arrays.stream(arr).filter(condition).collect(Collectors.toList());
T[] result=Arrays.copyOf(arr,tempList.size());
        return tempList.toArray(result);
    }
    String[]  convertToString(int[] arr){
       // Arrays.stream(arr).mapToObj(x->String.valueOf(x)).oArray();
    return null;
    }



//  int[]   filtrArray(int[] arr,IntPredicate condition){
//        int[] result=null;
//     List<Integer> tempList=new ArrayList<>();
//      Arrays.stream(arr).filter(condition).forEach(tempList::add);
//      result=new int[tempList.size()];
//      int i=0;
//      for (int temp:tempList ) {
//          result[i]=temp;
//          i++;
//      }
//  return result;
//    }
//    int[]   filtrArray2(int[] arr,IntPredicate condition){
//
//        return Arrays.stream(arr).filter(condition).toArray();
//    }
//
//    public static int[] arrayFilter(int[] num){
//
//        int[] result = Arrays.stream(num)
//                .map(x -> x * 2)
//                .toArray();
////        boxed()
////                .toArray(Integer[]::new);
//
//        return result;
//    }



}
