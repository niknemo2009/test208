import java.util.Arrays;

public class Proba {

    public static void main(String[] args) {
Sportable sport1= w -> System.out.println(w);
int arr[]={1,2,3};
FunctionOne fun=new FunctionOne();
FunctionTwo fun33=new FunctionTwo();
Proba proba=new Proba();
proba.modifyArrayI(arr,fun);
        System.out.println(Arrays.toString(arr));
        proba.modifyArrayI(arr,fun33);
        proba.modifyArrayI(arr,s->5*s+3);
        proba.modifyArrayI(arr,e->3*e*e+2*e+1);

    }

    void modifyArrayI(int[] arr,Algoritmic fun){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=fun.function(arr[i]);
        }
    }

// 5x+3    //7xx+4x-34
    public  void modifiArray(int[] arr,int koef,int digit){
        for(int i=0;i<arr.length;i++){
            arr[i]=koef*arr[i]+digit;
        }
    }

    public boolean isSimple(int digit){
      // boolean result=false;
       int counter=0;
        for(int i=1;i<=digit;i++){
            if(digit%i==0){
                counter++;
            }

        }
    return counter>2?false:true;
    }

}







