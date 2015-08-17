package ds;

/**
 * Created by grass on 7/31/15.
 */
public class SortBase {

    protected void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }



    protected void printData(int[] array) {
        StringBuffer sb = new StringBuffer();
        for (int val : array) {
            sb.append(val + ", ");
        }
        System.out.println(sb.toString());
    }

}
