package BoundaryCondition;

public class Power {

    public double pow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        return power(x, n);
    }

    private double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        System.out.println("x = " + x + "; n == " + n);
        double left = power(x, n / 2);
        System.out.println("left = " + left);
        double result = 0;
        if (n % 2 == 0) {
            result = left * left;
        } else if (n < 0) {
            result = left * left / x;
        } else {
            result = left * left * x;
        }
        System.out.println("result = " + result);
        return result;
    }

    public static void main(String[] args) {
        Power p = new Power();
        System.out.println(p.pow(2, 4));
    }
}
