package me.java.concurrency;

class Philosopher {
    private final int eatingSeconds;
    private int forksCount;
    private boolean eaten = false;

    Philosopher(int eatingSeconds, int forksCount) {
        this.eatingSeconds = eatingSeconds;
        this.forksCount = forksCount;
    }

    public void eatSpaghettiOnTable(Table table) {
        if (forksCount < 2)
            table.preemptFork();
        eat();
        table.addForks(forksCount);
        forksCount = 0;
    }

    private void eat() {
        try {
            Thread.sleep(eatingSeconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        eaten = true;
    }

    int getForksCount() {
        return forksCount;
    }

    boolean hasEaten() {
        return eaten;
    }
}
