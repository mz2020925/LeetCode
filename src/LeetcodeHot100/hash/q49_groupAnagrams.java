package LeetcodeHot100.hash;

import org.junit.Test;

import javax.xml.stream.events.Characters;
import java.util.*;
import java.util.stream.Collectors;

public class q49_groupAnagrams {
    @Test
    public void test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    /**
     * 哈希
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            return Arrays.toString(chars);

        }));
        System.out.println(res);
        return new ArrayList<>(res.values());

    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> str.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
        // collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)的意思：
        // 参数1：创建您的起始结果(在本例中是您的新StringBuilder)。
        // 参数2：向结果(StringBuilder)添加一个元素(String)。
        // 参数3：如果并行运行流，将创建多个stringbuilder。这是为了把它们组合在一起。
    }

}
