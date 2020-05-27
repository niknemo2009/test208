package lr1;

import javafx.beans.binding.StringBinding;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.*;
public class Main {


    public void vowelLetters(String text) {
        text = text.toLowerCase();
        char[] temp = text.toCharArray();
        char[] vowelLetters = {'а', 'е', 'и', 'і', 'о', 'у', 'я', 'ю', 'є', 'ї'};
        int count = 0;
        for (char letter : temp) {
            for (char symbol : vowelLetters) {
                if (letter == symbol) {
                    count++;
                }
            }
        }
        System.out.println("Number of vowels: " + count + " in: " + text);
    }

    public void printArray(String[] arr) {
        for (String element : arr) {
            System.out.print(element + "\t");
        }
    }

    public void stringSort(String[] str) {
        boolean swapped;
        for (int i = 0; i < str.length; i++) {
            swapped = false;
            for (int j = 0; j < str.length - i - 1; j++) {
                if (str[j].compareTo(str[j + 1]) > 0) {
                    String temp = str[j];
                    str[j] = str[j + 1];
                    str[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public String reverseString(String str) {
        char[] temp = str.toCharArray();
        char[] result = new char[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[(temp.length - 1) - i] = temp[i];
        }
        return new String(result);
    }

    public void changeCase(String str) {
        char[] temp = str.toCharArray();
        char[] result = new char[temp.length];
        for (int i = 0; i < temp.length; i++) {
            if (Character.isLowerCase(temp[i])) {
                result[i] = Character.toUpperCase(temp[i]);
            } else {
                result[i] = Character.toLowerCase(temp[i]);
            }
        }
        System.out.println(result);
    }

    public static String translit(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        Map<String, String> letters = new HashMap<String, String>();
        letters.put("а", "a");
        letters.put("б", "b");
        letters.put("в", "v");
        letters.put("г", "h");
        letters.put("ґ", "g");
        letters.put("д", "d");
        letters.put("е", "e");
        letters.put("є", "ye");
        letters.put("ж", "zh");
        letters.put("з", "z");
        letters.put("и", "y");
        letters.put("і", "i");
        letters.put("ї", "yi");
        letters.put("й", "y");
        letters.put("к", "k");
        letters.put("л", "l");
        letters.put("м", "m");
        letters.put("н", "n");
        letters.put("о", "o");
        letters.put("п", "p");
        letters.put("р", "r");
        letters.put("с", "s");
        letters.put("т", "t");
        letters.put("у", "u");
        letters.put("ф", "f");
        letters.put("х", "kh");
        letters.put("ц", "ts");
        letters.put("ч", "ch");
        letters.put("ш", "sh");
        letters.put("щ", "sh");
        letters.put("ю", "yu");
        letters.put("я", "ya");

        for (int i = 0; i < text.length(); i++) {
            String letter = text.substring(i, i + 1);
            if (letters.containsKey(letter)) {
                sb.append(letters.get(letter));
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }

    public static void translit2(String text) {
        char[] temp = text.toCharArray();
        String[] translitLetters = {"sh", "a", "s", "t", "ya"};
        String[] result = new String[text.length()];
        for (int i = 0; i < temp.length; i++) {
            String tmp = Character.toString(temp[i]);
            tmp = translitLetters[i];
            result[i] = tmp;
        }
        for (String letter : result) {
            System.out.print(letter);
        }
    }

    public static void main(String[] args) {
        //String str = "HeLLo woRlD!";
        //String s = "щастя";
        //translit2(s); //newVariant
        //System.out.println(translit(s));
        //r.changeCase(str); //task17

        /*
        String[] str = {"a", "b", "c", "b", "a"};
        String[] str2 = {"a", "a", "b", "c", "c"};
        r.stringSort(str); //task18
        r.printArray(str);
        r.stringSort(str2);
        r.printArray(str2);
         */

        //r.reverseString("hello");  //task 19
        //r.vowelLetters("найулюбленіші");  //task20
    }
}
