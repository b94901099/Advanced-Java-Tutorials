/*
 * 如何 serialize binary tree? 把tree存到檔案裡面再讀回來
 * 
 * 思路: preorder 才能夠找到順序復原
 */
package Google;

import java.util.*;
import java.io.*;

public class BSTSerialization {

    public static void BSTserialization(BSTree tree, String path) throws IOException {
        String res = tree.preOrderTraversal();
        FileWriter fstream = new FileWriter(path);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(res);
        out.close();
    }

// use this O(n) for faster reconstruct http://mattcb.blogspot.com/2012/12/reconstruct-bst-from-preorder.html
    public static BSTree BSTDeserialization(String path) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        BSTree tree = new BSTree();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split("\\s");
            for (String element : elements) {
                if (!element.equals("#")) {
                    tree.insert(Integer.valueOf(element));
                }
            }
        }
        reader.close();
        return tree;
    }

    public static void main(String[] ars) throws IOException {
        BSTree tree = new BSTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);
        tree.insert(2);
        tree.insert(4);
        tree.insert(10);
        tree.insert(11);
        for (int i = 12; i < 18; i++) {
            tree.insert(i);
        }
        for (int i = 25; i > 21; i--) {
            tree.insert(i);
        }
        System.out.println(tree.preOrderTraversal());
        String path = "c:\\cs\\out.txt";
        BSTserialization(tree, path);
        BSTree tree2 = BSTDeserialization(path);
        System.out.println();
        System.out.println(tree2.preOrderTraversal());

    }
}

class BSTree {

    TreeNode root = null;

    public void insert(int i) {
        if (root == null) {
        }
    }

    public String preOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    private void preOrderTraversal(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("# ");
            return;
        }
        sb.append(node.val + " ");
        preOrderTraversal(node.left, sb);
        preOrderTraversal(node.right, sb);
    }

    
    // 一般重建是 O(nlogn), 因為有n個點, 每次 insert 是 logn
    // 但是此法能縮減時間至 O(n), 主因在於他限定了最大值最小值, 
    // 對於任意點而言, 左子樹右子是
    public void preOrderBuild(int[] ar) {
        root = preOrderBuild(ar, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    int index = 0;

    private TreeNode preOrderBuild(int[] ar, int min, int max) {
        if (index >= ar.length) {
            return null;
        }
        if (ar[index] > max || ar[index] < min) {
            return null;
        }
        TreeNode node = new TreeNode(ar[index++]);
        node.left = preOrderBuild(ar, min, node.val);
        node.right = preOrderBuild(ar, node.val, max);
        return node;
    }
}
