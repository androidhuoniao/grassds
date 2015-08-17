package thinkinjava.generic;

/**
 * Created by grass on 8/10/15.
 */
public class Coffice {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
//        try {
//           Coffice coffice =  getClass().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return getClass().getSimpleName() + "  " + id;
    }


}
