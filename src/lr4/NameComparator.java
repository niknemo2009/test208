package lr4;
import java.util.Comparator;

public class NameComparator implements Comparator<Parking> {
    @Override
    public int compare(Parking p1, Parking p2){
        return p1.getNamePerson().compareTo(p2.getNamePerson());
    }
}
