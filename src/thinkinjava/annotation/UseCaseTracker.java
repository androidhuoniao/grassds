package thinkinjava.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by grass on 8/11/15.
 */
public class UseCaseTracker {

    public static void trackUseCases(List<Integer> usecases, Class<?> c) {
        for (Method m : c.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (null != uc) {
                System.out.println("Found UseCase id: " + uc.id() + " des: " + uc.description());
                usecases.remove(new Integer(uc.id()));
            }
        }

        for (int i : usecases) {
            System.out.println("Missing cases: " + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 12, 14, 49, 50);
        trackUseCases(useCases,PasswordUtils.class);
    }
}
