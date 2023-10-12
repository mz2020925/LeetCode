package LeetcodeBacktracking;

import java.util.ArrayList;
import java.util.List;

public class q22_Generate_Parentheses {
    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        System.out.println(res);
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        process(n, n, res, "");
        return res;
    }


    public static void process(int left, int right, List<String> res, String itemOfRes) {
        if (left == 0 && right == 0) {
            res.add(itemOfRes);
            return;
        }

        // if (left > right) {  // 因为下面两个判断条件，只要left > 0，就left - 1，所以一定是left先减小到 0，同时left一定 <= right
        //     return;
        // }

        if (left > 0) {
            process(left - 1, right, res, itemOfRes + "(");
        }

        if (left < right) {
            process(left, right - 1, res, itemOfRes + ")");
        }
    }
}
