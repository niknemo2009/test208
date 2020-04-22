package lesson2204;

public class MathUtil {

    int one(){
       return 0;
    }

    public int sum(int ... elements) {
        int result=0;
        if (elements.length == 0) {

            result=-1;
        }
        for (int temp:elements ) {
            if(temp%2==0){
                result+=temp;
            }

        }
        return result;
    }

    public int getDays(int numberOfMonth) {
        int result=0;
        int temp=numberOfMonth;
        result=(temp==1||temp==3||temp==5||temp==7||
                temp==8||temp==10||temp==12)?31:(temp==2?28:30);
        return result;
    }
}
