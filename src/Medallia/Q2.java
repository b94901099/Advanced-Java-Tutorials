package Medallia;

import java.util.*;

public class Q2 {

    private static HashMap<Node, Integer> map = new HashMap<Node, Integer>();

    /**
     * @return {@link List} of {@link Node}s which form the largest common
     * subtrees
     */
    private static List<Node> getLargestCommonSubtrees(Node root) {
        List<Node> result = new ArrayList<Node>();
        getHashCode(root);

        int[] tmp = new int[map.size()];
        int k = 0;
        for (int i : map.values()) {
            tmp[k++] = i;
        }
        Arrays.sort(tmp);

        int maxCommonValue = 0;

        for (int i = 1; i < tmp.length; i++) {
            if (tmp[i - 1] == tmp[i]) {
                maxCommonValue = tmp[i];
            }
            break;
        }
        Set<Node> kset = map.keySet();

        for (Node node : kset) {
            if (map.get(node) == maxCommonValue) {
                result.add(node);
            }
        }

        return result;
    }

    private static int getHashCode(Node root) {
        if (root == null) {
            return 0;
        }
        int hashCode = 0;
        for (int i = 0; i < root.children.size(); i++) {
            hashCode += getHashCode(root.children.get(i));
        }
        for (int i = 0; i < root.children.size(); i++) {
            hashCode += i * 31;
        }
        map.put(root, hashCode);
        return hashCode;
    }

    private static class Node {

        private final int id;
        private final List<Node> children;

        Node(int id) {
            this.id = id;
            this.children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

    /**
     * Useful for testing
     */
    private static void basicTest() {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        root.children.add(node1);
        root.children.add(node2);

        List<Node> result = getLargestCommonSubtrees(root);
        if (!result.contains(node1)) {
            throw new AssertionError(String.format("Expected to find node 1 but found nodes %s", result));
        }
        if (!result.contains(node2)) {
            throw new AssertionError(String.format("Expected to find node 2 but found nodes %s", result));
        }
    }

    public static void main(String[] args) {
        basicTest();
    }
}
