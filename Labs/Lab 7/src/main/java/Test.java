public class Test {
    public void testFunc() {
        boolean state = true;
        int iteration = 0;
        while(state) {
            int[] arr = {1, 2, 3};
            for (int val: arr) {
                switch (val){
                    case 1:
                        if( iteration < 10){
                            iteration++;
                        }
                    case 2:
                        for (int i = 0; i < 3; i++) {
                            iteration += i;
                        }
                    case 3:
                        if(iteration >= 10) {
                            state = false;
                        }
                }
            }
        }
    }

    public void hardcode(int a){
        //Данный метод не делает нечего полезного(Он нужен лишь для демонстрации поиска УК)
        if(a > 0) System.out.println("Param > 0");else if(a == 0)System.out.println("Param == 0");else
            System.out.println("Param < 0");int count=0;for(int i=0;i<10;i++){if(i==2)break;count++;}switch(count){case 1:
            System.out.println("1");case 2:while(count > 100)count++;case 3:while(count > 100)count++;}
    }
}
