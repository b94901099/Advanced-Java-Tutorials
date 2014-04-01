/*
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

 *    1
 *   /  \
 *  /    \
 *  0 --- 2
 *       / \
 *       \_/
 * 
 * 解法, 用一個 hash map 紀錄原有node 跟複製 node 對應關係,
 * 用 queue 紀錄原有 node, 每次拿出一個 current node, 做 curClone (要先查 curClone 是否存在於 map 之中)
 * 掃描並複製 current node 的 neighbors, 若該 neighbor node 已經存在於 map 中, 代表該 neighbor 的 clone
 * 也已經存在於 hash map 的 value, 則直接將該 neighborClone 加入 curClone 的 neighbor list,
 * 若該 neighbor node 不存在於 map 中, 代表該 neighbor 的 clone 尚未被建立, 因此建立 neighborClone,
 * 並放到 map 之中
 */
package Implement;

import java.util.*;

public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return null;
        }

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map =
                new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue =
                new LinkedList<UndirectedGraphNode>();

        UndirectedGraphNode cloneStart = new UndirectedGraphNode(node.label);
        map.put(node, cloneStart);
        queue.offer(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            UndirectedGraphNode curClone = map.get(cur);
            // 当前处理对象的复制品必定在map里，因为在前面的neighbor里已经被创建

            for (UndirectedGraphNode n : cur.neighbors) {
                if (map.containsKey(n)) {// 若當前 neighbor node 已經走過, 則不需要加入 queue 中
                    curClone.neighbors.add(map.get(n));
                } else { // 若當前 neighbor node 從沒走過, 則要加入 queue 中
                    UndirectedGraphNode neighborClone = new UndirectedGraphNode(n.label);
                    curClone.neighbors.add(neighborClone);
                    map.put(n, neighborClone);
                    queue.offer(n);
                }
            }
        }
        return cloneStart;
    }

    public static void main(String[] args) {
    }
}

class UndirectedGraphNode {

    int label;
    ArrayList<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};