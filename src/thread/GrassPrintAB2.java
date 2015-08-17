package thread;

/**
 * Created by grass on 8/13/15.
 */
public class GrassPrintAB2 {

    public static void main(String[] args) {
        GrassPrintAB2 printAB = new GrassPrintAB2();
        printAB.execute();
    }

    private volatile int mCount = 0;
    private static final int MAX_COUNT = 10;
    private Object mLock = new Object();

    public void execute() {

        new Thread(new RunnableA()).start();
        new Thread(new RunnableB()).start();
    }

    class RunnableA implements Runnable {

        @Override
        public void run() {
            synchronized (mLock) {
                while (mCount < MAX_COUNT) {
                    System.out.println("---A");
                    mCount++;
                    try {
                        mLock.wait();
                        mLock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    class RunnableB implements Runnable {

        @Override
        public void run() {
            synchronized (mLock) {
                while (mCount < MAX_COUNT) {
                    System.out.println("---B");
                    mCount++;
                    try {
                        mLock.notify();
                        mLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
