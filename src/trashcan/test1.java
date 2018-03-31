package trashcan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import com.cal.mold.*; // or import java.util.Stack;

/**
 * 本例主要使用了数据结构中的栈来实现,栈的实现可以使用java.util.Stack,这里我使用了自己编写的栈
 * 另外本例也使用了java类集中的ArrayList和HashMap
 * 
 * @author jonhou
 * @version 2016年2月5日下午6:32:58
 */
public class test1 {
    private static HashMap<String, Integer> hm = new HashMap<String, Integer>(5);

    public test1() {
        hm.put("+", 1);
        hm.put("-", 1);
        hm.put("*", 2);
        hm.put("/", 2);
        hm.put("%", 2);

    }

    private String[] split(String s) {
        String[] ss = null;
        StringBuilder strtmp = new StringBuilder();
        for (char x : s.toCharArray()) {
            if (x == '+' || x == '-' || x == '*' || x == '/' || x == ')' || x == '(') {
                strtmp.append(",");
                strtmp.append(x);
                strtmp.append(",");
            } else {
                strtmp.append(x);
            }
        }
        ss = strtmp.toString().replaceAll(",{2,}", ",").split(",");

        return ss;
    }

    /**
     * 判断输入的四则表达式格式是否合法
     * 
     * @param s
     * @return
     */
    private boolean isLegal(String s) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(split(s)));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("(")) {
                list.remove(i);
                stack.push(list.get(i));
                i--;
            } else if (list.get(i).equals(")")) {
                if (stack.peek() == null) {
                    System.out.println("wrong expression too more )");
                    return false;
                } else {
                    stack.pop();
                    list.remove(i);
                    i--;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("wrong expression too more (");
            return false;
        }
        if (!isNum(list.get(0))) {
            System.out.println("wrong expression first");
            return false;
        }
        System.out.println(list);
        for (int i = 0; i < list.size() - 1; i++) {
            if ((isNum(list.get(i)) && isNum(list.get(i + 1))) || (!isNum(list.get(i)) && !isNum(list.get(i + 1)))) {
                System.out.println("wrong expression double ");
                return false;
            }
        }
        return true;
    }

    private boolean isNum(String s) {
        for (char x : s.toCharArray()) {
            if (x >= '0' && x <= '9') {
                return true;
            }
        }
        return false;
    }

    /**
     * if top > x return true
     */
    private boolean isHigh(String top, String x) {

        if (x.equals("(") || top == null || top.equals("(")) {
            return false;
        }
        return hm.get(top) > hm.get(x);
    }

    public ArrayList<String> toRPN(String s) {
        if (!isLegal(s)) {
            System.exit(0);
        }
        ArrayList<String> RPN = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        String[] strSplit = split(s);
        for (String x : strSplit) {
            if (isNum(x)) {
                RPN.add(x);
            } else {
                if (x.equals(")")) { // 出现右括号,出栈至(
                    while (!"(".equals(stack.peek())) {
                        RPN.add(stack.pop());
                    }
                    stack.pop();
                } else if (isHigh(stack.peek(), x)) {// 栈顶元素较大的时候出栈
                    RPN.add(stack.pop());
                    while (stack.peek() != null && !isHigh(x, stack.peek())) {// 将优先级比x大的和相等的出栈
                        RPN.add(stack.pop());
                    }
                    stack.push(x);
                } else {
                    stack.push(x);
                }
            }

        }
        while (stack.peek() != null) {
            RPN.add(stack.pop());
        }
        return RPN;

    }

    public float calculate(String s) {
        ArrayList<String> RPN = toRPN(s);
        Stack<Float> stack = new Stack<Float>();
        for (String x : RPN) {
            if (isNum(x)) {
                stack.push(Float.valueOf(x));
            } else {
                float rear = stack.pop();
                float front = stack.pop();
                switch (x) {
                case "+":
                    stack.push(front + rear);
                    break;
                case "-":
                    stack.push(front - rear);
                    break;
                case "*":
                    stack.push(front * rear);
                    break;
                case "/":
                    stack.push(front / rear);
                    break;
                default:
                    System.out.println("wrong opration");
                }
            }
        }
        return stack.pop();
    }
    
    public static void main(String[] args) {  
        String str = "9-2-3-4";  
        test1 analyer = new test1();
        System.out.println(str+"="+analyer.calculate(str));  
    }  
}
