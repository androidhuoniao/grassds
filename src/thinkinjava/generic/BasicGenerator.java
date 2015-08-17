package thinkinjava.generic;

/**
 * Created by grass on 8/10/15.
 */

//为什么BasicGenerator后要加上<T>的声明
public class BasicGenerator<T> implements Generator<T> {

    //TODO private Class t;与private Class<T> type有什么区别？
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }


    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }

}
