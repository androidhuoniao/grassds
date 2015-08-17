package thinkinjava.generic;

import java.util.ArrayList;

/**
 * Created by grass on 8/10/15.
 */
public class TupleList<A,B> extends ArrayList<TwoTuple<A, B>> {

    public static void main(String[] args) {
        TupleList<String, Integer> tl = new TupleList<String, Integer>();
    }
    
}
