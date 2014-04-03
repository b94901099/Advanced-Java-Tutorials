/*
 * Print out the grade-school multiplication table up to 12x12
 */
package Google;

public class MultiplicationTable {

    public void multTables(int max) {
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= max; j++) {
                System.out.print(String.format("%4d", j * i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MultiplicationTable m = new MultiplicationTable();
        m.multTables(12);
    }
}
