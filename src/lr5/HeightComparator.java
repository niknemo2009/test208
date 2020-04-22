package lr5;
import java.util.Comparator;

public class HeightComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2){
        return s1.getHeight().compareTo(s2.getHeight());
    }
}
