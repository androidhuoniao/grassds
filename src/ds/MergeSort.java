package ds;

/**
 * Created by grass on 8/5/15.
 */
public class MergeSort extends SortBase {

    public static void main(String[] args) {

        int[] data = {11,3, 2, 1, 4, 6, 5, 7, 8, 10, 9};
        MergeSort sort = new MergeSort();
        data = sort.mergeSort(data);
        sort.printData(data);

    }

    public int[] mergeSort(int[] data) {
        int[] result = new int[data.length];
        sort(data, result, 0, data.length - 1);
        return result;
    }

    public void sort(int[] source, int[] result, int start, int end) {
        if (start == end) {
            result[0] = source[start];
            return;
        }
        int mid = (start + end) / 2;
        int[] array1 = new int[mid - start + 1];
        int[] array2 = new int[end - mid];
        sort(source, array1, start, mid);
        sort(source, array2, mid + 1, end);
        merge(result, array1, array2);
    }

    public int[] merge(int[] res, int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int k = 0;
        while ((i < a.length && j < b.length)) {
            if (a[i] < b[j]) {
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }
        if (i < a.length) {
            for (int index = i; i < a.length; i++) {
                res[k] = a[i];
                k++;
            }
        }

        if (j < b.length) {
            for (int index = j; j < b.length; j++) {
                res[k] = b[j];
                k++;
            }
        }
        return res;
    }

    public void sort2(int[] source, int[] result, int start, int end) {
        if (start == end) {
            result[0] = source[start];
            return;
        }
        int mid = (start + end) / 2;
        int[] array1 = new int[mid - start + 1];
        int[] array2 = new int[end - mid];
        sort(source, array1, start, mid);
        sort(source, array2, mid + 1, end);
        result = merge2(array1, array2);
        printData(result);

    }

    public int[] merge2(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] res = new int[a.length + b.length];
        while ((i < a.length && j < b.length)) {
            if (a[i] < b[j]) {
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }
        if (i < a.length) {
            for (int index = i; i < a.length; i++) {
                res[k] = a[i];
                k++;
            }
        }

        if (j < b.length) {
            for (int index = j; j < b.length; j++) {
                res[k] = b[j];
                k++;
            }
        }
        return res;
    }


}
