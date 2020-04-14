package Codility;


import java.util.HashMap;

class GFG
{

    public static class Tree {
        int data;
        Tree left, right;
    };

    public static Tree newNode(int data) {
        Tree temp = new Tree();
        temp.data = data;
        temp.left = temp.right = null;
        return temp;
    }

    public static int solution(Tree node) {

        if (node == null)
            return 0;
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        return largest(node, hash);
    }

    static int largest(Tree node, HashMap<Integer, Integer> m) {
        if (node == null)
            return m.size();

        if(m.containsKey(node.data)) {
            m.put(node.data, m.get(node.data) + 1);
        } else {
            m.put(node.data, 1);
        }

        System.out.println(m.toString());
        
        int max_path = Math.max(largest(node.left, m), largest(node.right, m));

        if(m.containsKey(node.data)) {
            m.put(node.data, m.get(node.data) - 1);
        }

        if (m.get(node.data) == 0)
            m.remove(node.data);



        return max_path;
    }

    public static void main(String[] args) {

        Tree root = newNode(1);
        root.right = newNode(2);
        root.right.left = newNode(1);
        root.right.right = newNode(1);
        root.right.right.left = newNode(4);

        System.out.println(solution(root));
    }
}