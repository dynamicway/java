package me.java.concurrency;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class DiningPhilosophers {
    private final int eatingSeconds;
    private final Table table;
    private final List<Philosopher> philosophers;

    DiningPhilosophers(int eatingSeconds, Table table, List<Philosopher> philosophers) {
        this.eatingSeconds = eatingSeconds;
        this.table = table;
        this.philosophers = philosophers;
    }

    void eat() {
        TimerTask eatingTask = readyEating();
        startEatingAndObserveEatingFinishedWithinTime(eatingTask);
    }

    private void startEatingAndObserveEatingFinishedWithinTime(TimerTask eatingTask) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> future = executorService.submit(eatingTask);
        try {
            future.get(eatingSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("It takes too long to eat.");
        }
    }

    private TimerTask readyEating() {
        return new TimerTask() {
            @Override
            public void run() {
                int philosophersCount = philosophers.size();
                ExecutorService executor = Executors.newFixedThreadPool(philosophersCount);
                CountDownLatch countDownLatch = new CountDownLatch(philosophersCount);
                for (final Philosopher philosopher : philosophers) {
                    executor.execute(() -> {
                        philosopher.eatSpaghettiOnTable(table);
                        countDownLatch.countDown();
                    });
                }
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    boolean hasEaten() {
        return philosophers.stream()
                           .allMatch(Philosopher::hasEaten);
    }
}
