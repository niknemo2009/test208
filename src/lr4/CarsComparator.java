package lr4;
import java.util.Collections;
import java.util.Comparator;


public class CarsComparator implements Comparator<Parking>{
    @Override
    public int compare(Parking p1, Parking p2){

            return p1.getCarModel().compareTo(p2.getCarModel());
    }

}
