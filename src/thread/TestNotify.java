package thread;

/**
 * Created by grass on 8/13/15.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类：测试 wait notify 用法
 * 顺次打印AB
 *
 * @author Abe
 */
public class TestNotify {
    public static void main(String[] args) {
        // 创建线程池 大小为2
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 分别初始化两条线程 并start
        String[] str = {"A", "B"};
        for (int i = 0; i < 2; i++) {
            service.execute(new MyPrinter(str[i], 10));
        }
        // 所有线程结束后 关闭线程池
        service.shutdown();
    }
}

/**
 * 内部类：线程运行内容 接口Runable并重写run方法
 *
 * @author Abe
 */
class MyPrinter implements Runnable {
    private String str;
    private int number;
    public static boolean check = false;
    private static Object obj = new Object();

    public MyPrinter(String str, int number) {
        this.str = str;
        this.number = number;
    }

    public void print() {
        System.out.print(str);
    }

    /**
     * 重写方法 ： 线程运行的方法  A     0锁定程序  2先唤醒等待池 3 A打印 4进入等待池，并解锁程序
     * B   1程序被锁定在程序外等待（非进入等待池）5程序解锁， 进入并锁定程序
     * 6先唤醒等待池 7此时A被唤醒，但是程序被锁，在程序外等待
     * 8 B打印 9 进入等待池，并解锁程序
     * A   从B-5开始重复循环。。。
     */
    @Override
    public void run() {
        //第一次只能打印为A的线程进来 之后check变true 所有线程都能进来 此句失效
        while (check == false && !this.str.equals("A")) {

        }
        for (int i = 0; i < number; i++) {
            synchronized (obj) {
                obj.notify();
                print();
                check = true;
                try {
                    //∞ 各自循环到自己的最后一次时 不再进入等待池，线程终止
                    if (i < number - 1) {
                        obj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
