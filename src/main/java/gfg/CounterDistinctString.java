package gfg;


/**
 * https://www.geeksforgeeks.org/count-distinct-substrings-string-using-suffix-trie/
 * <p>
 * Given a string of length n of lowercase alphabet characters,
 * we need to count total number of distinct substrings of this string.
 */
public class CounterDistinctString {

    static final int MAX_CHAR = 26;

    static class SuffixTrieNode {

        private SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];

        SuffixTrieNode() {
            for (int i = 0; i < MAX_CHAR; i++) {
                children[i] = null;
            }
        }

        public void insert(String s) {
            if (s.length() > 0) {
                char cIndex = (char) (s.charAt(0) - 'a');
                if (children[cIndex] == null) {
                    children[cIndex] = new SuffixTrieNode();
                }
                children[cIndex].insert(s.substring(1));
            }
        }
    }

    static class SuffixTrie {

        private SuffixTrieNode root;

        SuffixTrie(String s) {
            root = new SuffixTrieNode();
            for (int i = 0; i < s.length(); i++) {
                root.insert(s.substring(i));
            }
        }

        private int _countNodes(SuffixTrieNode node) {
            if (node == null)
                return 0;

            int count = 0;
            for (int i = 0; i < MAX_CHAR; i++) {
                if (node.children[i] != null) {
                    count += _countNodes(node.children[i]);
                }
            }

            return (1 + count);
        }

        public int count() {
            return _countNodes(root);
        }
    }

    static int countDistinctSubstring(String str) {

        SuffixTrie suffixTrie = new SuffixTrie(str);
        return suffixTrie.count();
    }

    public static void main(String... args) {

        String str = "ababa";
        System.out.println("Count of distinct substrings is "
                + countDistinctSubstring(str));
    }
}
