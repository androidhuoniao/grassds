package thread;

/**
 * Created by grass on 8/13/15.
 */
public class HelloThreadTest {
    public static void main(String[] args) {
        HelloThread r = new HelloThread();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

    }

}

class HelloThread implements Runnable {
    int i;

    @Override
    public void run() {

        while (true) {
            System.out.println("Hello number: " + i++);

            try {
                Thread.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (50 == i) {
                break;
            }
        }

    }
}