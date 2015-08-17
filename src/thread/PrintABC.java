package thread;

/**
 * Created by grass on 8/13/15.
 */
public class PrintABC implements Runnable {

    private static char[] chars = new char[]{'A', 'B', 'C'};
    private static Integer index = 0; // 临界资源
    private int count = 10;

    public static void main(String[] args) {
        new Thread(new PrintABC()).start();
        new Thread(new PrintABC()).start();
        new Thread(new PrintABC()).start();
    }

    public void run() {
        while (true) {
            synchronized (PrintABC.class) {
                if (index == 10) {
                    return;
                }
                System.out.println(chars[index % chars.length]);
                index++;
            }
        }
    }
}

