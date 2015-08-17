package ds;

/**
 * Created by grass on 7/31/15.
 */
public class InsertSort extends SortBase {

    public static void main(String[] args) {
        int[] data = {11, 5, 4, 7, 2, 1, 3, 6, 9, 8};
        InsertSort sort = new InsertSort();
        sort.insertSort2(data);
        sort.printData(data);

    }


    public void insertSort(int[] source) {
        int j = 0;
        for (int i = 1; i < source.length; i++) {
            int key = source[i];
            for (j = i - 1; j >= 0 && source[j] > key; j--) {
                source[j + 1] = source[j];
            }
            source[j + 1] = key;
        }
    }


    public void insertSort2(int[] source) {
        int i = 0;
        int k = 0;
        for (i = 1; i < source.length; i++) {
            int key = source[i];
            for (int j = 0; j < i; j++) {
                if (key < source[j]) {
                    for (k = i - 1; k >= j; k--) {
                        source[k + 1] = source[k];
                    }
                    source[j] = key;
                    break;
                }
            }
            printData(source);
        }
    }
}
