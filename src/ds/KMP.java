package ds;

/**
 * Created by grass on 8/5/15.
 */
public class KMP extends SortBase {

    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
        KMP kmp = new KMP();
        int[] next = kmp.getNextArray2(p);
        kmp.printData(next);

        next = kmp.getNextArray(p);
        kmp.printData(next);

        System.out.println("result: " + kmp.search(s, p));
        System.out.println("result: " + kmp.searchKmp(s, p));
    }

    private int[] getNextArray2(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < chars.length - 1) {
            if (k == -1 || chars[k] == chars[j]) {
                ++j;
                ++k;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    private int[] getNextArray(String str) {
        char[] chars = str.toCharArray();
        int k = -1;
        int j = 0;
        int length = chars.length;
        int[] next = new int[length];
        next[0] = -1;
        while (j < length - 1) {
            if (k == -1 || chars[k] == chars[j]) {
                ++j;
                ++k;
                if (chars[k] != chars[j]) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    private int search(String source, String pa) {
        int[] next = getNextArray2(pa);
        int i = 0;
        int j = 0;
        char[] sl = source.toCharArray();
        char[] pl = pa.toCharArray();
        while (i < sl.length && j < pl.length) {
            if (j == -1 || sl[i] == pl[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        int result = 0;
        if (j == pl.length) {
            result = i - j;
        } else {
            result = -1;
        }
        return result;
    }

    private int searchKmp(String source, String pattern) {
        int[] next = getNextArray(pattern);
        char[] sa = source.toCharArray();
        char[] pa = pattern.toCharArray();
        int sl = sa.length;
        int pl = pa.length;
        int k = 0;
        int j = 0;
        while (j < sl && k < pl) {
            if (k == -1 || sa[j] == pa[k]) {
                j++;
                k++;
            } else {
                k = next[k];
            }
        }
        int result = 0;
        if (k == pl) {
            result = j - k;
        } else {
            result = -1;
        }
        return result;
    }
}
