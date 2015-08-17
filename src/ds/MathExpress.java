package ds;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grass on 8/17/15.
 */
public class MathExpress {

    public static void main(String[] args) {
        MathExpress math = new MathExpress();
        math.execute();
    }

    private void execute() {
        String str = "9 + ( 3 - 1 ) * 3 + 10 / 2 ";
        ;
        String[] strs = computeTailExpress(str);
        for (String s : strs) {
            System.out.println("s: " + s);
        }
        compute(strs);

    }

    /**
     * 计算逆波兰式
     *
     * @param arrys
     * @return
     */
    private String compute(String[] arrys) {
        int pre;
        int next;
        int result;
        Stack<String> stack = new Stack<String>();
        for (int index = 0; index < arrys.length; index++) {
            String ch = arrys[index];
            if (ch.equals("+")) {
                next = Integer.parseInt(stack.pop());
                pre = Integer.parseInt(stack.pop());
                result = (pre + next);
                stack.push(result + "");
            } else if (ch.equals("-")) {
                next = Integer.parseInt(stack.pop());
                pre = Integer.parseInt(stack.pop());
                result = (pre - next);
                stack.push(result + "");
            } else if (ch.equals("*")) {
                next = Integer.parseInt(stack.pop());
                pre = Integer.parseInt(stack.pop());
                result = (pre * next);
                stack.push(result + "");
            } else if (ch.equals("/")) {
                next = Integer.parseInt(stack.pop());
                pre = Integer.parseInt(stack.pop());
                result = (pre / next);
                stack.push(result + "");
            } else {
                stack.push(ch);
            }
        }
        String reu = parseStack(stack);
        System.out.println("compute: " + reu);
        return reu;
    }


    /**
     * 获取逆波兰式
     *
     * @param str
     * @return
     */
    private String[] computeTailExpress(String str) {
        String[] arrays = str.split(" ");
        ArrayList<String> list = new ArrayList<String>(50);
        Stack<String> stack = new Stack<String>();
        for (String ch : arrays) {
            if (isNumeric(ch)) {
                list.add(ch);
            } else if (ch.equals("+") || ch.equals("-")) {
                if (stack.size() == 0) {
                    stack.push(ch);
                } else {
                    int size = stack.size();
                    for (int index = 0; index < size; index++) {
                        String tm = stack.peek();
                        if (!tm.equals("(")) {
                            list.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(ch);
                }
            } else if (ch.equals("*") || ch.equals("/")) {
                if (stack.size() == 0) {
                    stack.push(ch);
                } else {
                    //这个地方其实不需要出栈，出现问题的原因是3 / 2 结果为1， 如果是1.5的话就对了
                    int size = stack.size();
                    for (int index = 0; index < size; index++) {
                        String tm = stack.peek();
                        if (tm.equals("*") || tm.equals("/")) {
                            list.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(ch);
                }

            } else if (ch.equals("(")) {
                stack.push(ch);
            } else if (ch.equals(")")) {
                int size = stack.size();
                for (int index = 0; index < size; index++) {
                    String tm = stack.pop();
                    if (tm.equals("(")) {
                        break;
                    } else {
                        list.add(tm);
                    }
                }
            }
        }

        int size = stack.size();
        for (int index = 0; index < size; index++) {
            String tm = stack.pop();
            list.add(tm);
        }
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;
    }


    public void testPattern() {
        String str = "2*4+(2*50)";
        Pattern p;
        Matcher m;
        ArrayList<String> list = new ArrayList<String>(100);
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        p = Pattern.compile("\\d+(\\.\\d+)*");
        m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
            String temp = m.group();
            map.put(str.indexOf(temp), temp);
        }
        p = Pattern.compile("\\+|\\-|\\*|\\/|\\(|\\)");
        m = p.matcher(str);
        while (m.find()) {
            System.out.println("char: " + m.group());
            list.add(m.group());
            String temp = m.group();
            map.put(str.indexOf(temp), temp);
        }
        travseralHashmap(map);
    }

    private void travseralHashmap(HashMap<Integer, String> map) {
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        for (Map.Entry<Integer, String> en : set) {
            System.out.println("en: " + en.getKey() + "  " + en.getValue());
        }
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private String parseStack(Stack<String> stack) {
        StringBuffer sb = new StringBuffer();
        int size = stack.size();
        for (int index = 0; index < size; index++) {
            sb.append(stack.get(index) + " ");
        }
        return sb.toString();
    }

}
