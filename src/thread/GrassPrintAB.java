package thread;

/**
 * Created by grass on 8/13/15.
 */
public class GrassPrintAB {

    public static void main(String[] args) {
        GrassPrintAB printAB = new GrassPrintAB();
        printAB.execute();
    }

    public void execute() {
        new Thread(new PrintRunnable()).start();
    }

    private volatile int mCount;
    private char[] chars = {'A', 'B'};

    class PrintRunnable implements Runnable {

        @Override
        public void run() {
            while (mCount < 10) {
                synchronized (GrassPrintAB.class) {
                    System.out.println("-----" + chars[mCount % chars.length]);
                    mCount++;
                }
            }
        }
    }
}
