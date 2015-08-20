package pattern;

/**
 * Created by grass on 8/19/15.
 */
public class SinglePattern {

    private static SinglePattern mInstance;

    private SinglePattern() {

    }

    //第一种单例实现方式
    public static synchronized SinglePattern getmInstance() {
        if (null == mInstance) {
            mInstance = new SinglePattern();
        }
        return mInstance;
    }

    //第二种单例实现方式
    private static volatile SinglePattern mInstance2;

    public static SinglePattern getInstance2() {
        if (null == mInstance2) {
            synchronized (SinglePattern.class) {
                if (null == mInstance2) {
                    mInstance2 = new SinglePattern();
                }
            }
        }
        return mInstance;
    };

    //第三种单例实现方式
    private static class InstanceHolder {
        private static SinglePattern INSTANCE = new SinglePattern();
    }

    public static SinglePattern getInstance3() {
        return InstanceHolder.INSTANCE;
    }



}
