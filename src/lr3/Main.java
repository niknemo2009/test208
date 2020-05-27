package lr3;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Student testStudent = new Student();

        Comparator<Student> heightComparator = new HeightComparator();
        ArrayList<Student> sorted;
        sorted = testStudent.sortGroup(heightComparator, 208);
        for(Student item : sorted){
            System.out.println(item);
        }
    }
}
