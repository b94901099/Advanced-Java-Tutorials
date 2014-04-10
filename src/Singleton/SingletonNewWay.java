package Singleton;

public class SingletonNewWay {

    private static SingletonNewWay instance = new SingletonNewWay();

    private SingletonNewWay() {
    }

    public static SingletonNewWay getInstance() {
        return instance;
    }
}
