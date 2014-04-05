/*
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * http://blog.csdn.net/fightforyourdream/article/details/16879561
 * http://www.cnblogs.com/lautsie/p/3259724.html
 */
package List;

import java.util.*;

public class CopyListWithRandomPointer {

    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // copy next
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode curCopy = new RandomListNode(cur.label);
            curCopy.next = cur.next;
            cur.next = curCopy;
            cur = cur.next.next;
        }

        //copy random
        cur = head;
        while (cur != null) {
            RandomListNode curRand = cur.random;
            cur.next.random = curRand == null ? null : curRand.next;
            cur = cur.next.next;
        }

        //recover the original list
        cur = head;
        RandomListNode copyHead = head.next;
        while (cur != null) {
            RandomListNode curCopy = cur.next;
            cur.next = curCopy.next;
            curCopy.next = curCopy.next == null ? null : curCopy.next.next;
            cur = cur.next;
        }
        return copyHead;
    }

    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        RandomListNode n4 = new RandomListNode(4);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        head.random = n3;
        n2.random = n4;
        RandomListNode ret = copyRandomList3(head);
        while (ret != null) {
            System.out.println(ret.label);
            ret = ret.next;
        }
    }

    //有bug
    
    public static RandomListNode copyRandomList3(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode headClone = new RandomListNode(head.label);
        map.put(head, headClone);
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode curClone = map.get(cur);
            RandomListNode curNext = cur.next;
            if (map.containsKey(curNext)) {
                curClone.next = map.get(curNext);
            } else {
                if (cur.next != null) {
                    RandomListNode curNextClone = new RandomListNode(curNext.label);
                    map.put(curNext, curNextClone);
                    curClone.next = curNextClone;
                }
            }

            RandomListNode curRandom = cur.random;
            if (map.containsKey(curRandom)) {
                curClone.random = map.get(curRandom);
            } else {
                if (cur.random == null) {
                    curClone.random = null;
                } else {
                    RandomListNode curRandomClone = new RandomListNode(curRandom.label);
                    map.put(curNext, curRandomClone);
                    curClone.next = curRandomClone;
                }
            }

            cur = cur.next;
        }

        return headClone;
    }

    private static class RandomListNode {

        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}
