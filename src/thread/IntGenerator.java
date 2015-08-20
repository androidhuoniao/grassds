package thread;

/**
 * Created by grass on 8/20/15.
 */
public abstract class IntGenerator {

    private volatile boolean mCancled = false;

    public abstract int next();

    public void cancle() {
        mCancled = true;
    }

    public boolean isCancled() {

        return mCancled;
    }


}
