package BoundaryCondition;

public class Sqrt {

    public static void main(String[] args) {
        Sqrt s = new Sqrt();
        System.out.println(s.sqrt(5));
        System.out.println(s.sqrt(2));
        System.out.println(s.sqrt(50));
    }

    public int sqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int start = 0;
        int end = x;
        int mid = -1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            System.out.println("start: " + start + ", end: " + end + ", mid: " + mid);
            if (mid == x / mid) {
                return mid;
            }
            if (mid > x / mid) {
                end = mid;
            } else {
                start = mid;
            }
        }
        System.out.println("End while loop");
        System.out.println("start: " + start + ", end: " + end + ", mid: " + mid);
        if (start <= x / start) {
            return start;
        }
        return end;
    }
}
