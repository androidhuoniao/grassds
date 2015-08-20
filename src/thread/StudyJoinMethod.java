package thread;

import javax.print.attribute.standard.JobName;

/**
 * Created by grass on 8/20/15.
 */
public class StudyJoinMethod {

    public static void main(String[] args) {
        StudyJoinMethod sf = new StudyJoinMethod();
        sf.testJoin();
//        sf.testJoin2();
    }

    /**
     * 该例子演示了两个内容
     * 1. join分发的作用是调用该B thread方法的A thread等待B thrad执行完毕之后，再去执行A thread中的内容
     * 2. 如果B thread被interruped之后，这两个线程都结束了。
     */
    public void testJoin() {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumy = new Sleeper("Grumy", 1500);

        Joiner dopey = new Joiner("Dopey", sleepy);
        Joiner doc = new Joiner("Doc", grumy);

        grumy.interrupt();

    }

    /**
     * Java Thread中， join() 方法主要是让调用该方法的thread完成run方法里面的东西后， 再执行join()方法后面的代码
     * t1启动后，调用join()方法，直到t1的计数任务结束，才轮到t2启动，然后t2也开始计数任务。可以看到，实例中，两个线程就按着严格的顺序来执行了。
     * 如果t2的执行需要依赖于t1中的完整数据的时候，这种方法就可以很好的确保两个线程的同步性。
     */
    public void testJoin2() {
        Thread t1 = new Thread(new ThreadTesterA());
        Thread t2 = new Thread(new ThreadTesterB());
        t1.start();
        try {
            t1.join(); // wait t1 to be finished
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join(); // in this program, this may be removed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Sleeper extends Thread {

        private int mDuaation;
        private String mName;

        public Sleeper(String name, int sleepTime) {
            super(name);
            this.mDuaation = sleepTime;
            this.mName = name;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(mDuaation);

            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted " + isInterrupted());
                return;
            }
            System.out.println(getName() + " has awakened");
        }
    }

    class Joiner extends Thread {

        private Sleeper mSleeper;

        public Joiner(String name, Sleeper sleeper) {
            super(name);
            this.mSleeper = sleeper;
            start();
        }

        @Override
        public void run() {
            try {
                mSleeper.join();
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
            }

            System.out.println(getName() + " join has completed");

        }
    }

    class ThreadTesterA implements Runnable {

        private int counter;

        @Override
        public void run() {
            while (counter <= 10) {
                System.out.print("Counter = " + counter + " ");
                counter++;
            }
            System.out.println();
        }
    }

    class ThreadTesterB implements Runnable {

        private int i;

        @Override
        public void run() {
            while (i <= 10) {
                System.out.print("i = " + i + " ");
                i++;
            }
            System.out.println();
        }
    }


}
