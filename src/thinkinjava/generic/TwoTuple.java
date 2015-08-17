package thinkinjava.generic;

/**
 * Created by grass on 8/10/15.
 */
public class TwoTuple<A,B> {

    public final A mA;
    public final B mB;

    public TwoTuple(A a, B b) {
        this.mA = a;
        this.mB = b;
    }

    @Override
    public String toString() {
        return ""+mA+" "+mB;
    }
}
