package BinarySearch;

/**
 * 查找代码库的第一个坏版本。有一个SVN代码库，版本号从1到N，
 * 自从某个阿三提交了他的代码之后，代码库就坏掉了程序跑不起来了，
 * 也就是自从阿三提交的这个版本及之后的所有版本都是坏的代码版本。
 * 现在有一个接口，isBadVersion(int version)，可以用于判断version这个编号的代码是否是好的。
 * 请你利用这个接口，来找到阿三提交的那个坏版本的编号。
 * 即，找到第一个i，使得isBadVersion(i-1) == false and isBadVersion(i) == true。 
 */
    
public class FindBadVersion {

    boolean isBadVersion(int i) {
        if (i < 10) {
            return true;
        }
        return false;
    }

    int findBadVersion(int n) {
        if (n < 2) {
            if (n == 1 && isBadVersion(1)) {
                return 1;
            }
            return -1;
        }
        if (isBadVersion(1)) {
            return 1;
        }
        int start = 2;
        int end = n;
        while (start + 1 < n) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid) && !isBadVersion(mid - 1)) {
                return mid;
            } else if (isBadVersion(mid - 1)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if(isBadVersion(start) && !isBadVersion(start - 1)){
            return start;
        }
        if(isBadVersion(end) && !isBadVersion(start)){
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
