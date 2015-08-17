package thinkinjava.generic;

/**
 * Created by grass on 8/10/15.
 */
public class BasicGeneratorNoGeneric {

    private Class type;

    public BasicGeneratorNoGeneric(Class type) {
        this.type = type;
    }


    public Object next() {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static BasicGeneratorNoGeneric create(Class type) {
        return new BasicGeneratorNoGeneric(type);
    }
}
