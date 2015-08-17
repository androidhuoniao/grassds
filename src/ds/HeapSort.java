package ds;

/**
 * Created by grass on 7/31/15.
 */
public class HeapSort extends SortBase {


    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        sort.heapSort();
    }

    public void heapSort() {
        int[] source = {1, 10, 5, 4, 7, 2, 1, 3, 6, 9, 8};
        int size = (source.length - 1) / 2;
        for (int index = size; index >= 1; index--) {
            heapAdjust(source, index, source.length - 1);
        }
        printData(source);

        for (int i = source.length - 1; i > 1; i--) {
            swap(source, 1, i);
            printData(source);
            heapAdjust(source, 1, i - 1);
        }
        printData(source);
    }


    private void heapAdjust(int[] data, int parentindex, int lastIndex) {
        if (parentindex >= lastIndex) {
            return;
        }
        int parVal = data[parentindex];
        for (int i = 2 * parentindex; i < lastIndex; i = i * 2) {
            if (data[i] < data[i + 1]) {
                i++;
            }
            if (parVal > data[i]) {
                break;
            }
            data[parentindex] = data[i];
            parentindex = i;
        }
        data[parentindex] = parVal;

    }
}

