package lr1;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static int cols = 3;
    static int rows = 3;

    public void Ex1() {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите первый диапозон чисел");
        int a = s.nextInt();
        int b = s.nextInt();
        System.out.println("Введите второй диапозон чисел");
        int a1 = s.nextInt();
        int b1 = s.nextInt();
        int size;
        if (a > b) {
            a++;
            size = a - b;
        } else {
            b++;
            size = b - a;
        }
        int size1;
        if (a1 > b1) {
            a1++;
            size1 = a1 - b1;
        } else {
            b1++;
            size1 = b1 - a1;
        }
        int arr1[] = new int[size];
        int arr2[] = new int[size1];
        for (int i = 0; i < size; i++) {
            if (a > b) {
                arr1[i] = b;
                b++;
            } else {
                arr1[i] = a;
                a++;
            }
        }
        System.out.println(Arrays.toString(arr1));
        for (int i = 0; i < size1; i++) {
            if (a1 > b1) {
                arr2[i] = b1;
                b1++;
            } else {
                arr2[i] = a1;
                a1++;
            }

        }
        System.out.println(Arrays.toString(arr2));
        for (int i = 0; i < size; i++) {
            int aa = arr1[i];
            for (int j = 0; j < size1; j++) {
                if (aa == arr2[j]) {
                    System.out.println(aa);
                }
            }
        }
    }

    public void Ex2(int[][] arr1, int[][] arr2) {
        System.out.println(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            int val = (int) (Math.random() * 3 + 1);
            arr2[i] = new int[val];
        }
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                arr1[i][j] = (int) (Math.random() * 40 + 10);
            }
        }
        System.out.println("First array");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(Arrays.toString(arr1[i]));
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                arr2[i][j] = (int) (Math.random() * 40 + 10);
            }
        }
        System.out.println("Second array");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(Arrays.toString(arr2[i]));
        }
        System.out.println("The same elements");
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                int element = arr1[i][j];
                for (int m = 0; m < arr2.length; m++) {
                    for (int n = 0; n < arr2[m].length; n++) {
                        if (element == arr2[m][n]) {
                            System.out.println(element);
                        }
                    }
                }
            }
        }
    }

    public void Ex3(int[] arr) {
        try {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 10);
            }
            Arrays.sort(arr);
            System.out.println(Arrays.toString(arr));
            int element = -1;
            for (int i = 0; i < arr.length; i++) {
                if (element != arr[i]) {
                    element = arr[i];
                    System.out.print("\n" + element + "-" + count(element, arr));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int count(int element, int[] arr) {
        int count = 0;
        for (int n : arr)
            if (n == element) count++;
        return count;
    }

    public void Ex4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        while (n != 0) {
            sum += (n % 10);
            System.out.println(sum + "    " + n);
            n = n / 10;
            System.out.println(sum + "    " + n);
        }
        System.out.println(sum);
    }

    public void Ex5() {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        StringBuffer str1 = new StringBuffer(str);
        str1.reverse();
        System.out.println(str1);
    }

    public static void main(String[] args) {
        Main Ex = new Main();
        System.out.println("Ведите номер задания от 1 до 5");
        Scanner s = new Scanner(System.in);
        int value = s.nextInt();
        switch (value) {
            case 1:
                Ex.Ex1();
                break;
            case 2:
                int[][] arr = new int[cols][rows];
                int[][] arr1 = new int[cols][];
                Ex.Ex2(arr, arr1);
                break;
            case 3:
                int[] arr2 = new int[10];
                Ex.Ex3(arr2);
                break;
            case 4:
                Ex.Ex4();
                break;
            case 5:
                Ex.Ex5();
                break;
            default:
                System.out.println("Такого задания нет");
        }
    }
}

