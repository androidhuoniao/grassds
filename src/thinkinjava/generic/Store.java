package thinkinjava.generic;

import java.util.ArrayList;

/**
 * Created by grass on 8/10/15.
 */
public class Store extends ArrayList<Product.Aisle> {


    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Product.Aisle(nShelves, nProducts));
        }
    }


    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Product.Aisle a : this) {
            for (Product.Shelf s : a) {
                for (Product p : s) {
                    result.append(p).append("\n");

                }
            }
        }
        return result.toString();
    }


}
