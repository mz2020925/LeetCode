package LeetcodeBacktracking;

import java.util.*;

public class q17_Letter_Combinations_Of_A_Phone_Number {
    public static void main(String[] args) {
        List<String> res = letterCombinations("");
        System.out.println(res);
        System.out.println(res);
    }

    public static List<String> letterCombinations(String digits) {
        Map<Character, Character[]> nums = new HashMap<>();
        nums.put('2', new Character[]{'a', 'b', 'c'});
        nums.put('3', new Character[]{'d', 'e', 'f'});
        nums.put('4', new Character[]{'g', 'h', 'i'});
        nums.put('5', new Character[]{'j', 'k', 'l'});
        nums.put('6', new Character[]{'m', 'n', 'o'});
        nums.put('7', new Character[]{'p', 'q', 'r', 's'});
        nums.put('8', new Character[]{'t', 'u', 'v'});
        nums.put('9', new Character[]{'w', 'x', 'y', 'z'});
        List<String> res = new ArrayList<>();
        if (digits.equals("")){
            return res;
        }
        process(digits, nums, res, "");
        return res;
    }

    public static void process(String digits, Map<Character, Character[]> nums, List<String> res, String itemOfRes){
        if (digits.equals("")){
            res.add(itemOfRes);
            return;
        }
        for (Character character : nums.get(digits.charAt(0))) {
            process(digits.substring(1), nums, res, itemOfRes + character);
        }


    }
}
