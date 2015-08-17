package thinkinjava.generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by grass on 8/10/15.
 */
public class CofficeGenerator implements Generator<Coffice>, Iterable<Coffice> {


    @Override
    public Coffice next() {
        return null;
    }

    @Override
    public Iterator<Coffice> iterator() {
        return null;
    }

    public <T, A, B> void f(T x, A a, B b) {
        System.out.println("name " + x.getClass().getSimpleName() + " " + a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        CofficeGenerator generator = new CofficeGenerator();
        generator.f("123", "1", 2);
        generator.f(generator, true, 1.0f);

    }

    //这是一种什么写法啊？为什么去掉static 后面的<K, V>，就编译不过去了呢？因为要定义泛型方法，只需要将泛型参数列表置于返回值之前
    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();

    }

}
