package thread;

import java.util.concurrent.*;

/**
 * Created by grass on 8/20/15.
 */
public class StudyFir {

    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool();
//        es.execute(new TestRunnable());
        StudyFir sf = new StudyFir();
//        sf.testCallable();
        sf.testSubmitMethod();
    }




    private void testCallable(){
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> result = es.submit(new TestCallable());
        System.out.println("complete");

//        try {
//            System.out.println("result "+result.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public void testJoinMethod(){

    }

    public void testSubmitMethod(){
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future result = es.submit(new TestRunnable());
        try {
            System.out.println("testSubmitMethod: "+result.isDone()+" resu: "+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    class TestCallable implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            Thread.sleep(3*1000);
            return false;
        }
    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("executor");


        }
    }


}
