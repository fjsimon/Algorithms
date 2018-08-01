package HackerRank.CrackingTheCodingInterview.DataStructures.Tries;

import java.util.*;
import java.util.regex.Pattern;

public class TriesContact {

    private static final Scanner scanner = new Scanner(System.in);

    static class Node {
        private static int NUMBER = 26;
        Node[] children = new Node[NUMBER];
        int size = 0;

        private static int getCharIndex(char c) {
            return c - 'a';
        }

        private Node getNode(char c) {
            return children[getCharIndex(c)];
        }

        private void setNode(char c, Node d) {
            children[getCharIndex(c)] = d;
        }

        public void add(String s) {
            add(s, 0);
        }

        private void add(String s, int index) {
            size++;
            if(index == s.length()) return;
            char current = s.charAt(index);
            Node child = getNode(current);
            if(child == null){
                child = new Node();
                setNode(current, child);
            }
            child.add(s, index + 1);
        }

        public int findCount(String s, int index) {
            if(index == s.length()){
                return size;
            }
            Node child = getNode(s.charAt(index));
            if(child == null) return 0;
            return child.findCount(s, index + 1);
        }
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Node trie = new Node();

        for (int nItr = 0; nItr < n; nItr++) {
            String[] opContact = scanner.nextLine().split(" ");

            String op = opContact[0];
            String contact = opContact[1];

            Pattern pattern = Pattern.compile("^[a-z]{1,21}");
            if(pattern.matcher(contact).matches()) {
                if (op.equals("add")) {
                    trie.add(contact);
                } else {
                    System.out.println(trie.findCount(contact, 0));
                }
            }
        }

        scanner.close();
    }
}