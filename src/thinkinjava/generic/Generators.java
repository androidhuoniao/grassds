package thinkinjava.generic;

import java.util.Collection;

/**
 * Created by grass on 8/10/15.
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int index = 0; index < n; index++) {
            coll.add(gen.next());
        }
        return coll;
    }
}
