package LeetcodeHot100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class q438_Find_Anagrams {
    @Test
    public void test() {
        String s = "baa";
        String p = "aa";
        List<Integer> res = findAnagrams(s, p);
        System.out.println(res);
    }

    public List<Integer> findAnagrams(String s, String p) {
        HashMap<String, Integer> need = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            String temp = p.substring(i, i + 1);
            need.put(temp, need.getOrDefault(temp, 0) + 1);
        }

        HashMap<String, Integer> window = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            // 一个元素待移入窗口
            String r = s.substring(right, right + 1);
            // 右指针++
            right++;
            // 进行窗口数据操作
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (need.get(r).equals(window.get(r))) {
                    valid++;
                }
            }


            // 判断左边是否需要收缩，注意左右指针式左闭右开，窗口满足长度的时候也需要收缩，因为要滑动，下一个循环又会有一个元素要进来
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                String l = s.substring(left, left + 1);
                left++;
                if (need.containsKey(l)) {
                    if (need.get(l).equals(window.get(l))) {
                        valid--;
                    }
                    window.put(l, window.getOrDefault(l, 0) - 1);
                }

            }
        }
        return res;
    }

    /**
     * 超时了
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        char[] pChars = p.toCharArray();
        Arrays.sort(pChars);
        String pStr = Arrays.toString(pChars);
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i <= sLen - pLen; i++) {
            char[] tempChars = s.substring(i, i + pLen).toCharArray();
            Arrays.sort(tempChars);
            String tempStr = Arrays.toString(tempChars);
            if (pStr.equals(tempStr)) {
                res.add(i);
            }
        }

        return res;
    }
}
