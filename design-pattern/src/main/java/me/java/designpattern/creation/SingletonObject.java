package me.java.designpattern.creation;

import java.util.Random;

public class SingletonObject {

    private static SingletonObject singletonInstanceWithoutConcurrency;

    private final int randomValue;

    private SingletonObject() {
        this.randomValue = new Random().nextInt();
    }

    public static SingletonObject getInstanceWithoutConcurrency() {
        if (singletonInstanceWithoutConcurrency == null) {
            sleep(1000);
            singletonInstanceWithoutConcurrency = new SingletonObject();
        }
        return singletonInstanceWithoutConcurrency;
    }

    private static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
