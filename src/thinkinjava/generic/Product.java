package thinkinjava.generic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by grass on 8/10/15.
 */
public class Product {

    private final int id;
    private String description;
    private double price;

    public Product(int id, String descr, double pri) {
        this.id = id;
        description = descr;
        price = pri;
    }

    @Override
    public String toString() {
        return "id: " + id + " des: " + description + " price: " + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {

        private Random rand = new Random(47);

        @Override
        public Product next() {
            return new Product(rand.nextInt(1000), "Test", Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };

    public static class Shelf extends ArrayList<Product> {
        public Shelf(int nProducts) {
            Generators.fill(this, Product.generator, nProducts);
        }
    }

    public static class Aisle extends ArrayList<Shelf> {
        public Aisle(int nShelves, int nProducts) {
            for (int i = 0; i < nShelves; i++) {
                add(new Shelf(nProducts));
            }
        }
    }
}
