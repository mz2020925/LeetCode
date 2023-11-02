package LeetcodeHot100;

import org.junit.Test;
import java.util.HashSet;

public class q3_Length_Of_Longest_Substring {
    @Test
    public void test() {
        String s = "abcabcbb";
        int res = lengthOfLongestSubstring3(s);
        System.out.println(res);
    }

    /**
     * 这种方式的时间复杂度比下面那种方式的时间复杂度高
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int i = 0;
        int res = 1;
        for (int j = 1; j < s.length(); j++) {
            String substring = s.substring(i, j);
            String anChar = s.substring(j, j + 1);
            if (substring.contains(anChar)) {
                while (substring.contains(anChar)) {
                    i++;
                    substring = s.substring(i, j);
                }
            }
            res = Math.max(res, j - i + 1);
        }

        return res;
    }

    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int res = 0;
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            temp++;
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(left));
                left++;
                temp--;
            }
            set.add(s.charAt(i));
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * 使用滑动窗口模板
     */
    public int lengthOfLongestSubstring3(String s) {
        HashSet<String> window = new HashSet<>();

        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            // 右边字符移入窗口
            String r = s.substring(right, right + 1);
            // 右扩窗口
            right++;
            if (!window.contains(r)){
                window.add(r);
            }else {
                // 判断左边字符是否需要移出窗口
                while (window.contains(r)) {
                    // 左边字符移出窗口
                    String l = s.substring(left, left + 1);
                    // 左缩窗口
                    left++;
                    window.remove(l);
                }
                window.add(r);
            }
            res = Math.max(res, window.size());
        }

        return res;
    }
}
