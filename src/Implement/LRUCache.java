/*
 * Design and implement a data structure for Least Recently Used (LRUCache) cache.
 * It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. 
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * 
 * 兩種解法, 第一種用 doubly linked list, 每個 node 都存在一個 hashmap 中, 
 * (key, value) = (node.val, node)
 * first node 是最近用過的, tail 是最久之前的
 * 第二種用 linked hash map
 * 
 * 
 */
package Implement;

import java.util.*;


public class LRUCache {

    private int capacity = 0;
    private Node head;
    private Node tail;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-2, -2);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node cur = map.get(key);
        remove(cur);
        addFirst(cur);
        return cur.value;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node toUpdate = map.get(key);
            toUpdate.value = value;
            remove(toUpdate);
            addFirst(toUpdate);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() >= capacity) {
                Node toRemove = tail.pre;
                map.remove(toRemove.key);
                remove(toRemove);
            }
            addFirst(newNode);
            map.put(key, newNode);
        }
    }

    private void addFirst(Node cur) {
        cur.next = head.next;
        cur.next.pre = cur;
        head.next = cur;
        cur.pre = head;
    }

    private void remove(Node cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }

    private static class Node {

        private int key;
        private int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {
    }
}

// 解法2: 用 LinkdedHashMap

class LRUCache_LinkdedHashMap {

    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRUCache_LinkdedHashMap(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int val = map.get(key);
            map.remove(key);
            map.put(key, val); //更新放到最後面一個
            return val;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        } else {
            if (map.size() == capacity) {
                int firstKey = map.keySet().iterator().next();
                map.remove(firstKey);
            }
            map.put(key, value);
        }
    }
}
