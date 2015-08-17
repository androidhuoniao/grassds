package exam;

/**
 * Created by grass on 8/4/15.
 */
public class Xiaomi {

    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
    }

    private int[] getNextArray(String pattern) {
        char[] chars = pattern.toCharArray();

        int[] next = new int[chars.length];
        next[0] = 0;
        int j = 0;
        for (int index = 1; index < chars.length; index++) {
            if (chars[j] == chars[index]) {
                next[index] = next[j] + 1;
            }else{
                next[j] = 0;
            }
        }
        return next;
    }
}
