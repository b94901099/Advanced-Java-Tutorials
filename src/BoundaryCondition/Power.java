package BoundaryCondition;

public class Power {

    /*
     * 接下来我们介绍二分法的解法，如同我们在Sqrt(x)的方法。不过这道题用递归来解比较容易理解，
     * 把x的n次方划分成两个x的n/2次方相乘，然后递归求解子问题，结束条件是n为0返回1。
     * 因为是对n进行二分，算法复杂度和上面方法一样，也是O(logn)。
     */
    double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = pow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else if (n > 0) {
            return half * half * x;
        } else {
            return half / x * half;
        }
    }

    public double pow2(double x, int n) {
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
