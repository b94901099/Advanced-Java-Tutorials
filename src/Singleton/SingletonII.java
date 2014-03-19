package Singleton;

public class SingletonII {

    private static SingletonII singleton = null;

    private SingletonII() {
    }

    public static SingletonII getSingleton() {
        if (singleton == null) {
            synchronized (SingletonII.class) {
                if (singleton == null) {
                    singleton = new SingletonII();
                }
            }
        }
        return singleton;
    }
}
