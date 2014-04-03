/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Google;

public class RGBToHex {

    public String formatRGB(int r, int g, int b) {
        return (toHex(r) + toHex(g) + toHex(b)).toUpperCase();
    }

    public String toHex(int c) {
        String s = Integer.toHexString(c);
        return (s.length() == 1) ? "0" + s : s;
    }

    public static void main(String[] args) {
        String s = Integer.toHexString(16);
        System.out.println(s);
    }
}
