package thinkinjava.generic;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by grass on 8/10/15.
 */
public class Sets {

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.removeAll(b);
        return result;
    }

    public enum WaterColors{
        RED,YELLOW,ORIANGE,WIHTE,BLACK,BLUE,GREEN
    }


}
