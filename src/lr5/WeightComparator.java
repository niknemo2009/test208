package lr5;
import java.util.Comparator;

public class WeightComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getWeight().compareTo(s2.getWeight());
    }
}
