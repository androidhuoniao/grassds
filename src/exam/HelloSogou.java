package exam;


public class HelloSogou {
    public static synchronized void main(String[] a) {
        Thread t = new Thread() {
            public void run() {
                Sogou();
            }
        };
        t.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.print("Hello");
        HelloSogou sou = new HelloSogou();
        sou.test();
    }

    static synchronized void Sogou() {
        System.out.print("Sogou");
    }

    public void add(Byte b) {
        b = b++;
    }

    public void test() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }
}
