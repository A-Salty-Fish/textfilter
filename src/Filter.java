import java.util.HashMap;

/**
 * @author dzy
 * @title: Filter
 * @projectName textfilter
 * @description: TODO
 * @date 2022/1/12 10:11
 */
public class Filter {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("测试");
        trie.add("测试123");
        System.out.println(trie.preMatch("测试", 0));
        System.out.println(trie.preMatch("测试1232", 0));
        System.out.println(trie.preMatch("测测试1232", 1));
        System.out.println(trie.preMatch("测试2", 0));
        System.out.println(trie.preMatch("测试123", 1));
        System.out.println(trie.preMatch("测", 0));
        System.out.println(trie.preMatch("", 0));
    }
    public static class Trie {
        class Node {
            private boolean end = false;
            private HashMap<Character, Node> children = new HashMap<>();
        }

        Node root = new Node();

        /**
         * 添加某个匹配字符串
         * @param word
         */
        public void add(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                Node next = cur.children.get(c);
                if (next == null) {
                    next = new Node();
                    cur.children.put(c, next);
                }
                cur = next;
                if (i == word.length() - 1) {
                    cur.end = true;
                }
            }
        }

        /**
         * 返回最长匹配长度
         * @param word
         * @return
         */
        public int preMatch(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                Node next = cur.children.get(word.charAt(i));
                if (next == null) {
                    if (cur.end) {
                        return i;
                    } else {
                        return 0;
                    }
                }
                cur = next;
            }
            return cur.end ? word.length() : 0;
        }

        /**
         * 返回最长匹配长度
         * @param word
         * @return
         */
        public int preMatch(String word, int begin) {
            Node cur = root;
            for (int i = begin; i < word.length(); i++) {
                Node next = cur.children.get(word.charAt(i));
                if (next == null) {
                    if (cur.end) {
                        return i - begin;
                    } else {
                        return 0;
                    }
                }
                cur = next;
            }
            return cur.end ? word.length() : 0;
        }
    }
}
