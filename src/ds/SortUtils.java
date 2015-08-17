package ds;

import java.util.Arrays;

/**
 * Created by grass on 7/29/15.
 */
public class SortUtils {

    private int[] mData = new int[]{11, 4, 2, 5, 1, 8, 6, 7, 10, 3, 9,12};

    public static void main(String[] args) {
        SortUtils sort = new SortUtils();
//        sort.bubbleSort();
//        sort.simpleSelectSort();
//        sort.insertSort2();
        sort.shellSort();
//        sort.heapSort();
//        sort.heapSortSelf();
//        sort.quickSort();
//        sort.mergeSort();
//        sort.mergeSortNoTraversal();
    }

    public void bubbleSort() {
        boolean flag = true;
        for (int i = 0; i < mData.length && flag; i++) {
            flag = false;
            for (int j = mData.length - 1; j > i; j--) {
                int aval = mData[j];
                int bval = mData[j - 1];
                if (bval > aval) {
                    mData[j] = bval;
                    mData[j - 1] = aval;
                    flag = true;
                }
            }
        }
    }

    public void simpleSelectSort() {
        int min = 0;
        for (int i = 0; i < mData.length; i++) {
            min = i;
            for (int j = i + 1; j < mData.length; j++) {
                if (mData[min] > mData[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int aval = mData[i];
                int bval = mData[min];
                mData[i] = bval;
                mData[min] = aval;
            }
        }
    }


    public void insertSort2() {
        int i, j = 0;
        for (i = 1; i < mData.length; i++) {
            j = i - 1;
            int temp = mData[i];
            for (; j >= 0 && mData[j] > temp; j--) {
                mData[j + 1] = mData[j];
            }
            mData[j + 1] = temp;
        }

        printData();
    }


    public void insertSort() {

        int a[] = {4, 2, 5, 1, 8, 6, 7, 10, 3, 9};

        int temp = 0;

        for (int i = 1; i < a.length; i++) {

            int j = i - 1;

            temp = a[i];

            for (; j >= 0 && a[j] > temp; j--) {

                a[j + 1] = a[j];                       //将大于temp的值整体后移一个单位

            }

            a[j + 1] = temp;

        }

        for (int i = 0; i < a.length; i++)

            System.out.println(a[i]);

    }


    public void shellSort() {
        int a[] = {11, 9, 1, 5, 8, 3, 7, 4, 6, 2, 10};
        int i, j, temp = 0;
        int increment = a.length;
        do {
            increment = increment / 3 + 1;
            for (i = increment; i < a.length; i++) {
                if (a[i] < a[i - increment]) {//小于前面的元素，需要将a[i]前移
                    temp = a[i];
                    for (j = i - increment; j >= 0 && temp < a[j]; j = j - increment) {//根据increment间隔不断的前移
                        a[j + increment] = a[j];
                    }
                    a[j + increment] = temp;
                }
            }

        } while (increment > 1);

        for (int index = 0; index < a.length; index++)
            System.out.println(a[index]);

    }

    public void printData() {
        StringBuffer sb = new StringBuffer();
        for (int val : mData) {
            sb.append(val + ", ");
        }
        System.out.println(sb.toString());
    }

    public void printData(String tag) {
        StringBuffer sb = new StringBuffer();
        sb.append(tag + "   ");
        for (int val : mData) {
            sb.append(val + ", ");
        }
        System.out.println(sb.toString());
    }


    public void printData(int[] array) {
        StringBuffer sb = new StringBuffer();
        for (int val : array) {
            sb.append(val + ", ");
        }
        System.out.println(sb.toString());
    }

    /////////////////////heap sort///////////////////////////////////////////

    public void heapSort() {
        int a[] = {9, 1, 5, 8, 3, 7, 4, 6, 2};
        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    //对data数组从0到lastIndex建大顶堆
    private void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }

        }
    }


    public void heapSortSelf() {
        mData = new int[]{1, 8, 6, 1, 10, 4, 2, 3, 5, 9, 11, 12, 7};
        for (int i = (mData.length - 1) / 2; i > 0; i--) {
            heapAdjust(i, mData.length);
        }
        printData();
        for (int j = mData.length - 1; j > 1; j--) {
            swap(1, j);
            printData("--" + j + "--");
            heapAdjust(1, j);
            printData("==" + j + "==");
        }
        printData();
    }


    /**
     * m 是剩余的数组的长度
     *
     * @param s
     * @param m
     */
    public void heapAdjust(int s, int m) {
        int temp, j = 0;
        temp = mData[s];
        for (j = 2 * s; j < m; j = j * 2) {
            if ((j < m - 1) && mData[j] < mData[j + 1]) {
                j++;
            }
            if (temp > mData[j]) {
                break;
            }
            mData[s] = mData[j];
            s = j;
        }
        mData[s] = temp;
    }

    private void swap(int i, int j) {
        int temp = mData[i];
        mData[i] = mData[j];
        mData[j] = temp;
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void quickSort() {
        qSort(0, mData.length - 1);
        printData();
    }

    private void qSort(int low, int heigh) {
        int mid = 0;
        while (low < heigh) {
            mid = partition(low, heigh);
            qSort(low, mid - 1);
            low = mid + 1;
        }
    }


    private int partition(int low, int heigh) {
        int midkey = mData[low];
        while (low < heigh) {
            while (low < heigh && mData[heigh] >= midkey) {
                heigh--;
            }
            swap(low, heigh);
            while (low < heigh && mData[low] <= midkey) {
                low++;
            }
            swap(low, heigh);
        }
        return low;
    }


    public void mergeSort() {
        int[] sortArray = new int[mData.length];
        msort(mData, sortArray, 0, mData.length - 1);
    }

    private void msort(int[] sr, int[] tr, int s, int t) {
        int m = 0;
        int[] array2 = new int[mData.length];
        if (s == t) {
            tr[s] = mData[s];
        } else {
            m = (s + t) / 2;
            msort(sr, array2, s, m);
            msort(sr, array2, m + 1, t);
            merge(array2, tr, s, m, t);
        }
        printData(tr);
    }

    private void merge(int[] sr, int[] tr, int i, int m, int n) {

        int j, k = 0;
        for (j = m + 1, k = i; i <= m && j <= n; k++) {
            if (sr[i] < sr[j]) {
                tr[k] = sr[i];
                i++;
            } else {
                tr[k] = sr[j];
                j++;
            }
        }
        if (i <= m) {
            for (int l = 0; l <= m - i; l++) {
                tr[k + l] = sr[i + l];
            }
        }

        if (j <= n) {
            for (int l = 0; l <= n - j; l++) {
                tr[k + l] = sr[j + l];
            }
        }
        printData(tr);

    }


    public void mergeSortNoTraversal() {
        int[] result = new int[mData.length];
        int k = 1;
        while (k < mData.length) {
            mergePass(mData, result, k, mData.length - 1);
            k = 2 * k;
            mergePass(result, mData, k, mData.length - 1);
        }
        printData(result);
    }

    /**
     * k 代表两两合并还是四四合并
     *
     * @param sr
     * @param tr
     * @param s
     * @param n
     */
    private void mergePass(int[] sr, int[] tr, int s, int n) {
        int i = 0;
        int j = 0;
        while (i <= n - 2 * s + 1) {
            merge(sr, tr, i, i + s, i + 2 * s);
            i = i + 2 * s + 1;
        }

        if (i < n - s + 1) {
//            merge(sr, tr, i, i + n) /2, n);
        } else {
            for (j = i; j <= n; j++) {
                tr[j] = sr[j];
            }
        }
    }




}