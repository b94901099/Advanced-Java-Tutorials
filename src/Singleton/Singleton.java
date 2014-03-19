package Singleton;
/* Multi-thread 問題
 * 若是多個threads同時呼叫getInstance()
 * 則要用SingletonII
 */

public class Singleton {

    private static Singleton singleton = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
