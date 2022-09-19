package me.java.designpattern.creation.singleton;

import java.util.Random;

public class SingletonObject {

    private static SingletonObject singletonInstanceWithoutConcurrency;
    private static SingletonObject singletonInstanceWithSynchronized;

    private static class SingletonObjectHolder {
        private static final SingletonObject SINGLETON_INSTANCE_WITH_HOLDER = new SingletonObject();
    }

    private final int randomValue;

    private SingletonObject() {
        this.randomValue = new Random().nextInt();
    }

    public static SingletonObject getInstanceWithoutConcurrency() {
        initializedWithoutConcurrency();
        return singletonInstanceWithoutConcurrency;
    }

    private static void initializedWithoutConcurrency() {
        if (singletonInstanceWithoutConcurrency == null) {
            sleep(1000);
            singletonInstanceWithoutConcurrency = new SingletonObject();
        }
    }

    private static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static SingletonObject getInstanceWithSynchronized() {
        initializedSingletonWithSynchronized();
        return singletonInstanceWithSynchronized;
    }

    private static void initializedSingletonWithSynchronized() {
        if (singletonInstanceWithSynchronized == null) {
            synchronized (SingletonObject.class) {
                if (singletonInstanceWithSynchronized == null) {
                    sleep(1000);
                    singletonInstanceWithSynchronized = new SingletonObject();
                }
            }
        }
    }

    public static SingletonObject getInstanceWithHolder() {
        return SingletonObjectHolder.SINGLETON_INSTANCE_WITH_HOLDER;
    }
}
