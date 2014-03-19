/*
 * 现在要把M本有顺序的书分给K个人复制（抄写），每一个人的抄写速度都一样，
 * 一本书不允许给两个（或以上）的人抄写，分给每一个人的书，必须是连续的，
 * 比如不能把第一、第三、第四本数给同一个人抄写。
 * 现在请你设计一种方案，使得复制时间最短。复制时间为抄写页数最多的人用去的时间。
 * input：
 * 第一行两个整数M、K；（K<=10000 M<=1000000  满足 k<=m）
 * 第二行M个整数，第i个整数表示第i本书的页数。
 * Output：
 * 書目分配
 * Sample input：
 * 9 3
 * 1 2 3 4 5 6 7 8 9
 * Sample output：
 * 1 5
 * 6 7
 * 8 9
 */
package BinarySearch;

import Implement.LinkedList;
import java.util.Queue;

public class CopyBooks {
    
    public int[][] dispatch(int m, int k, int mp[]){
        int[][] disp = new int[k][2];
            return disp;
    }
    
    public static void main(String[] args) {
        Queue<String> q = (Queue<String>) new LinkedList<String>();
        
    }
}
