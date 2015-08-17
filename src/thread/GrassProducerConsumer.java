package thread;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by grass on 8/14/15.
 */
public class GrassProducerConsumer {

    public static void main(String[] args) {
        GrassProducerConsumer grass = new GrassProducerConsumer();
        grass.execute();
    }

    private void execute() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        new Thread(new Productor(list)).start();
        new Thread(new Consumer(list)).start();
    }

    private static final int MAX_COUNT = 20;
    private Random mRandom = new Random();


    class Productor implements Runnable {

        private LinkedList<Integer> mList;

        public Productor(LinkedList<Integer> list) {
            this.mList = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (mList) {
                    while (isFull()) {
                        try {
                            System.out.println("Queue is full, Productor is waiting");
                            mList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("---------Productor is working");
                    mList.add(getRandomInt());
                    mList.notifyAll();
                }
            }
        }

        private boolean isFull() {
            return mList.size() == MAX_COUNT;
        }
    }


    class Consumer implements Runnable {

        private LinkedList<Integer> mList;

        public Consumer(LinkedList<Integer> list) {
            this.mList = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (mList) {
                    while (isEmpty()) {
                        try {
                            System.out.println("Queue is empty, Productor is waiting");
                            mList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("=======Consumer is working");
                    mList.remove();
                    mList.notifyAll();
                }
            }
        }

        private boolean isEmpty() {
            return mList.isEmpty();
        }
    }


    public int getRandomInt() {
        return mRandom.nextInt(1000);
    }

}
