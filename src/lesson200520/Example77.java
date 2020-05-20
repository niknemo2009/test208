package lesson200520;

import static java.lang.Thread.sleep;

public class Example77 {
    static int var=30;
    {
        System.out.println("Init 1");
    }
   static {
        System.out.println("static Init ");
    }
    {
        System.out.println("Init 2");
    }

    public static void main(String[] args) throws InterruptedException {
      MyThread33 qwe=  new MyThread33("Potok one");
        System.out.println(qwe.getState());
        qwe.start();
        int counter =0;
        while(qwe.isAlive()){
            if(counter>30){
                System.out.println(qwe.getState()+" ?????????");
                qwe.interrupt();
                System.out.println(qwe.getState()+" ?????????");

            }

        }
        //qwe.join();
       // Thread.sleep(1000);
        System.out.println(qwe.getState()+" !!!!!!!!!!!!!!!!!!!");
//        new MyThread33("Potok two").start();
//        new Thread(()->{
//            for(int i=0;i<30;i++){
//                System.out.println(i+" "+Thread.currentThread().getName());
//            }
//        }," Potok Runn Lam").start();
//        new MyThreadR("Ptok 55").currentThread.start();
    }

}
class MyThreadR implements  Runnable{
    Thread currentThread;

    public MyThreadR(String name) {
        currentThread=new Thread(this,name);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        for(int i=0;i<30;i++){
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}
class MyThread33 extends  Thread{
    public MyThread33(String name) {
        super(name);
    }

    @Override
    public void run() {

//        for(int i=0;i<30;i++){
//            System.out.println(i+" "+Thread.currentThread().getName());
//            System.out.println(i+" "+Thread.currentThread().getPriority());
            System.out.println(Thread.currentThread().getState());
//            System.out.println(i+" "+Thread.currentThread().getId());
//            System.out.println(i+" "+Thread.currentThread().getThreadGroup().getName());
//
//        }
            }
}


