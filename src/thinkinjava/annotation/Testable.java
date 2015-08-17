package thinkinjava.annotation;

/**
 * Created by grass on 8/11/15.
 */
public class Testable {

    public void execute() {
        System.out.println("executing.....");

    }

//    @Test
    void testExecute(){
        execute();;
    }

}
