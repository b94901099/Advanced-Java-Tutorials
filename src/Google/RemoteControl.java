/*
 * char = [ [a,b,c,d,e],
 *          [f,g,h,i,j],
 *          [k,l,m,n,o],
 *          [p,q,r,s,t],
 *          [u,v,w,x,y],
 *          [z] ]
 * 
 * 上下左右移動, 輸出string
 * 例如 google
 * a->g->o->o->g
 * output = "dr!drrr!!ulll!"
 * 
 */
package Google;

public class RemoteControl {

    int[][] chars = {
        {'a', 'b', 'c', 'd', 'e'},
        {'f', 'g', 'h', 'i', 'j'},
        {'k', 'l', 'm', 'n', 'o'},
        {'p', 'q', 'r', 's', 't'},
        {'u', 'v', 'w', 'x', 'y'},
        {'z'}
    };

    public String control(String input) {
        int[] pos = {0, 0};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            int cur = input.charAt(i) - 'a';
            int[] curPos = {cur / 5, cur % 5};

            while (curPos[0] > pos[0]) {
                sb.append('d');
                pos[0]++;
            }
            while (curPos[0] < pos[0]) {
                sb.append('u');
                pos[0]--;
            }
            while (curPos[1] > pos[1]) {
                sb.append('r');
                pos[1]++;
            }
            while (curPos[1] < pos[1]) {
                sb.append('u');
                pos[1]--;
            }
            sb.append('!');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoteControl r = new RemoteControl();
        System.out.println(r.control("google"));
    }
}
