package lr2;
import javax.naming.AuthenticationException;
import java.beans.MethodDescriptor;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.*;
public class Main {
    private void  GUESS(String[] arr){
        int ran=(int) (Math.random()*arr.length);
        char[] word_star=arr[ran].toCharArray();
        char[] word=arr[ran].toCharArray();
        System.out.println("\n");
        for(int i=0;i<word_star.length;i++){
            word_star[i]='*';
        }
        for(int i = 0; i < word_star.length; i++) {
            System.out.print(word_star[i] + " ");
        }
        System.out.println("\nYou have 10 attempts to guess the word");
        int trying=1;
        int trying1=10;
        while(trying<11) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            char[] symbol = str.toCharArray();
            for (int i = 0; i < word.length; i++) {
                if (symbol[0] == word[i]) {
                    word_star[i]=symbol[0];
                }
            }
            for (int i = 0; i < word_star.length; i++) {
                System.out.print(word_star[i] + " ");
            }
            if(Arrays.equals(word_star, word)){
                trying=11;
            }
            trying1--;
            System.out.println("\nNow you have "+ trying1 + " qattempts to guess the word");
            if(trying1==0 || trying==11){
                System.out.println("\nGame over");
            }
            trying++;
        }
    }
    private void CopyOf(int arr[]){
        int arr2[]=Arrays.copyOf(arr,5);
        System.out.println("CopyOf\n");
        System.out.println("ToString\n"+Arrays.toString(arr2));
    }
    private void CopyOfRangeAndEquals(int arr[]){
        int arr2[]=Arrays.copyOfRange(arr,1,3);
        System.out.println("Arrays are the same?: "+Arrays.equals(arr,arr2));
        System.out.println("CopyOfRange");
        System.out.println("ToString\n"+Arrays.toString(arr2));
    }
    private void Sort(int arr[]){
        Arrays.sort(arr);
        System.out.println("Sort");
        System.out.println("ToString\n"+Arrays.toString(arr));
    }
    /*private void UnSort(int arr[]){
        Arrays.sort(arr,Collections.reverseOrder());
        System.out.println("UnSort");
        System.out.println(Arrays.toString(arr));
    }*/
    private void ToString(int arr[]){
        System.out.println("ToString\n"+Arrays.toString(arr));
    }
    private void AsList(){
        Integer arr[] = new Integer[] { 10, 20, 30, 40 };
        List<Integer> list=Arrays.asList(arr);
        System.out.println("AsList\n"+list);
    }
    private void HashCode(int arr[]){
        System.out.println("HashCode of array: "+Arrays.hashCode(arr));
    }
    private void Fill(int arr[]){
        int a=228;
        Arrays.fill(arr,a);
        System.out.println("Fill\n"+Arrays.toString(arr));
    }
    private void BinarySearch(int arr[]){
        int a = 7;
        System.out.println("BinarySearch: "+Arrays.binarySearch(arr,a));
    }
    private void Mismatch(int arr[]){
        int arr2[]=Arrays.copyOfRange(arr,1,3);
        System.out.println("Mismatch\n"+Arrays.mismatch(arr,arr2));
    }
    private void ParallelSort(int arr[]){
        Arrays.parallelSort(arr);
        System.out.println("ParallelSort");
        System.out.println("ToString\n"+Arrays.toString(arr));
    }
    private void ParallelPrefix(int arr[]){
        Arrays.parallelPrefix(arr, (x,y) -> x + y);
        System.out.println("ParallelPrefix");
        System.out.println("ToString\n"+Arrays.toString(arr));
    }
    private void Compare(int arr[]){
        int arr2[]=Arrays.copyOf(arr,5);
        System.out.println("Compare\n"+Arrays.compare(arr,arr2));
    }
    private void SetAll(int arr[]){
        int x= 2;
        Arrays.setAll(arr,(index)->index+x);
        System.out.println(Arrays.toString(arr));
    }
    private void ParallelSetAll(int arr[]){
        int x= 10;
        Arrays.parallelSetAll(arr,(index)->index*x);
        System.out.println(Arrays.toString(arr));
    }



    private void ExpTryCatch() {
        int a=1,b=0, c;
        try{
            c=a/b;
        }
        catch (ArithmeticException ex){
            System.out.println("You can't divison by zero(try-catch)");
        }
    }
    private void ExpTtrows()throws ArithmeticException{
        int a=1,b=0, c;
        c=a/b;
    }


    public static void main(String[] args) throws AutorizationException {

        User user = new User();
        user.CheckUser("mario","3233");


        //ex1
        Main main=new Main();
        String[] arr1={"Hello","how","are","you"};
        main.GUESS(arr1);


        //Ex2
        int []arr=new int[5];
        for(int i=0;i<arr.length;i++){
            arr[i]=((int)(Math.random()*20-10));
            System.out.println(arr[i]);
        }
        //main.CopyOf(arr);
        //main.CopyOfRangeAndEquals(arr);
        //main.Sort(arr);
        //main.UnSort(arr);
        //main.ToString(arr);
        //main.AsList();
        //main.HashCode(arr);
        //main.Fill(arr);
        //main.BinarySearch(arr);//
        //main.Mismatch(arr);
        //main.ParallelSort(arr);
        //main.ParallelPrefix(arr);
        //main.Compare(arr);
        main.SetAll(arr);
        //main.ParallelSetAll(arr);


        //ex3
        main.ExpTryCatch();
        try {
            main.ExpTtrows();
        }
        catch (ArithmeticException ax){
            System.out.println("You can't divison by zero(throws)");
        }

        //4
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do you want to change salary and weekends? \n Yes - 1 No - 0");
        int answer = scanner.nextInt();
        if(answer ==1){
            Company1.Fixation();
        }
        Company1[] com=Company1.values();
        for(Company1 item: com){
            System.out.println("worker: "+item+" salary: "+item.salary+" weekends: "+item.weekends);
        }
    }
}
enum Company1{
    PROLE(1000,2),
    FOREMAN(1500,2),
    MANAGER(2000,3);
    void SetSalaryAndWeekends(int salary, int weekends){
        this.salary=salary;
        this.weekends=weekends;
    }
    Company1(int salary, int weekends){
        this.salary=salary;
        this.weekends=weekends;
    }
    public static Company1 Fixation(){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Choose the worker: \n Prole - 0 \n Foreman - 1 \n Manager - 2");
        int answer=scanner.nextInt();
        if(answer==0){
            System.out.println("Input new salary and weekends");
            int NewSalary=scanner.nextInt();
            int NewWeekends=scanner.nextInt();
            Company1.PROLE.SetSalaryAndWeekends(NewSalary,NewWeekends);
            return Company1.PROLE;
        }
        if(answer==1){
            System.out.println("Input new salary and weekends");
            int NewSalary=scanner.nextInt();
            int NewWeekends=scanner.nextInt();
            Company1.FOREMAN.SetSalaryAndWeekends(NewSalary,NewWeekends);
            return Company1.FOREMAN;
        }
        if(answer==2){
            System.out.println("Input new salary and weekends");
            int NewSalary=scanner.nextInt();
            int NewWeekends=scanner.nextInt();
            Company1.MANAGER.SetSalaryAndWeekends(NewSalary,NewWeekends);
            return Company1.MANAGER;
        }
        else {
            throw new NoSuchElementException("We don't have this worker!");
        }
    }
    int salary;
    int weekends;
}

