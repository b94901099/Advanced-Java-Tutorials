/*
 * You are given a string which has numbers and letters. Numbers occupy all odd positions
 * and letters even positions. You need to transform this string such that all letters
 * move to front of array, and all numbers at the end. 
 * The relative order of the letters and numbers needs to be preserved 
 * I need to do this in O(n) time and O(1) space. 
 * eg: a1b2c3d4 -> abcd1234 , x3y4z6 -> xyz346 
 * Sol
 * Transfer to ASCII
 * res = res * 10 + i
 * asc = asc * 100 + (c - 'a')
 */
package CodeChallange;

public class A1B2C3D4 {

    public static void main(String[] args) {
    }
}
