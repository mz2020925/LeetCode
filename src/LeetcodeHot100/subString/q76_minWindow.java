package LeetcodeHot100.subString;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class q76_minWindow {
    @Test
    public void test01() {
        // String s = "ADOBECODEBANC";
        String s = "a";
        String t = "aa";
        String res = minWindow(s, t);
        System.out.println(res);
    }


    // 滑动窗口
    public String minWindow(String s, String t) {
        // 根据字符串t构建hash表
        Map<String, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            String temp = t.substring(i, i + 1);
            need.put(temp, need.getOrDefault(temp, 0) + 1);
        }

        Map<String, Integer> window = new HashMap<>();
        String res = "";
        int len = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            // 右扩得到的元素
            String r = s.substring(right, right + 1);
            // 右边界右移，保证了左闭右开
            right++;

            // 将字符r加入窗口内,need里面包含这个key，才加入
            if (need.containsKey(r)) {  // 不断增加j使滑动窗口增大，直到窗口包含了T的所有元素，也就是满足了valid == need.size()
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) {
                    valid++;
                }
            }

            // 当满足了valid == need.size()的时候，开始左缩
            if (valid == need.size()) {
                String l = s.substring(left, left + 1);
                while (!need.containsKey(l) || (need.containsKey(l) && window.get(l) > need.get(l))) {
                    if (need.containsKey(l)) window.put(l, window.get(l) - 1);
                    left++;
                    l = s.substring(left, left + 1);
                }
                // 左缩一直缩到一个不能再缩的元素，计算res
                if (0 < right - left && right - left <= len) {
                    res = s.substring(left, right);
                    len = right - left;
                }
                // 计算完res之后，left++，会导致那个必须的元素被除外了
                left++;
                window.put(l, window.get(l) - 1);
                valid--;  // 此时已经不满足valid == need.size()了，因为left
                // 继续收缩左边界
                while (left <= right && !need.containsKey(s.substring(left, left + 1))) {
                    left++;
                }
            }
        }
        return res;
    }
}
