package thinkinjava.generic;

/**
 * Created by grass on 8/10/15.
 */
public class BasicGenericDemo {

    public static void main(String[] args) {
        Generator<CountedObject> generator = BasicGenerator.create(CountedObject.class);
        for (int index = 0; index < 5; index++) {
            System.out.println(" " + generator.next());
        }

        Generator<Coffice> cofficeGenerator = BasicGenerator.create(Coffice.class);
        for (int index = 0; index < 5; index++) {
            System.out.println(" " + cofficeGenerator.next());
        }

        BasicGeneratorNoGeneric generatorNoGeneric = BasicGeneratorNoGeneric.create(CountedObject.class);
        for (int index = 0; index < 5; index++) {
//            CountedObject obj = generatorNoGeneric.next();
            System.out.println(" " + generatorNoGeneric.next());
        }

    }


}
