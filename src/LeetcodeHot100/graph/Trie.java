package LeetcodeHot100.graph;


class TrieNode {
    public int path;
    public int end;
    public TrieNode[] nexts;

    public TrieNode() {
        path = 0;
        end = 0;
        nexts = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }

        char[] chars = word.toCharArray();
        int index = 0;
        TrieNode temp = root;
        temp.path++;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (temp.nexts[index] == null) {
                temp.nexts[index] = new TrieNode();
            }
            temp = temp.nexts[index];
            temp.path++;
        }
        temp.end++;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        char[] chars = word.toCharArray();
        int index = 0;
        TrieNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (temp.nexts[index] == null) {
                return false;
            }
            temp = temp.nexts[index];
        }
        return temp.end == 0 ? false : true;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        char[] chars = prefix.toCharArray();
        int index = 0;
        TrieNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (temp.nexts[index] == null) {
                return false;
            }
            temp = temp.nexts[index];
        }
        return temp.path >= 1 ? true : false;
    }
}
