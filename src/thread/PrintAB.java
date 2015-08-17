package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by grass on 8/13/15.
 */

public class PrintAB {
    public static String s = "";
    private volatile  int mCount;
    public static void main(String[] args) {

        Executor e = Executors.newFixedThreadPool(2); //最多两个同时运行的线程池
        e.execute(new Runnable() {
            int count;
            public void run() {
                synchronized (s) {
                    while (count < 10) {            //死循环 一直打印A吧
                        System.out.print("A");
                        count++;
                        try {
                            System.out.println("before A wait is working");
                            s.wait(); //等待
                            System.out.println("after A wait is working");
                            s.notify(); //有则唤醒 这些都要s来完成
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        });

        e.execute(new Runnable() {
            int count;
            public void run() {
                synchronized (s) {
                    while (count < 10) {
                        System.out.print("B");
                        count++;
                        try {
                            s.notify();                    //唤醒打印A的线程
                            System.out.println("B notifiy is working");
                            s.wait(1000);//自己则等待
                            System.out.println("B wait is working");
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
    }

}
