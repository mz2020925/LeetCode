package LeetcodeBacktracking;

import java.util.HashMap;
import java.util.Stack;

public class q20_Valid_Parentheses {
    public static void main(String[] args) {
        String s = "()[]}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (char charOfS : s.toCharArray()) {
            if (map.containsKey(charOfS)){  // 只要是左括号就入栈
                stack.push(charOfS);
            }else if(map.containsValue(charOfS)){  // 只要是右括号，就弹出栈顶的左括号，看是否匹配
                if (stack.isEmpty()){  // 当给定字符串中右括号比左括号多的时候，这里就会发生栈空的情况
                    return false;
                }
                char temp = stack.pop();
                if (map.get(temp) != charOfS){
                    return false;
                }
            }
        }
        return stack.isEmpty();  // 当给定字符串左括号比右括号多的时候，这里就会发生栈非空的情况
    }
}
