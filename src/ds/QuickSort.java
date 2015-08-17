package ds;

/**
 * Created by grass on 7/31/15.
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] data = {11, 5, 4, 7, 2, 1, 3, 6, 9, 8};
        QuickSort sort = new QuickSort();
        sort.quickSort(data);
    }


    public void quickSort(int[] source) {
        qSort(source, 0, source.length - 1);
        printData(source);
    }

    public void qSort(int[] source, int low, int heigh) {
        if (low < heigh) {
            int mid = partition(source, low, heigh);
            qSort(source, low, mid);
            qSort(source, mid + 1, heigh);
        }
    }

    public int partition(int[] source, int low, int heigh) {
        int key = source[low];
        while (low < heigh) {
            while (low < heigh && source[heigh] >= key) {
                heigh--;
            }
            swap(source, low, heigh);
            while (low < heigh && source[low] <= key) {
                low++;
            }
            swap(source, low, heigh);

            System.out.println("low: " + low + " heigh: " + heigh);
        }
        return low;
    }


    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void printData(int[] array) {
        StringBuffer sb = new StringBuffer();
        for (int val : array) {
            sb.append(val + ", ");
        }
        System.out.println(sb.toString());
    }


}
