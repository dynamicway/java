package me.java.concurrency;

class Philosopher {
    private static final int MINIMUM_NUMBER_OF_FORKS_TO_EAT = 2;
    private final int eatingSeconds;
    private int forksCount;
    private boolean eaten = false;

    Philosopher(int eatingSeconds, int forksCount) {
        this.eatingSeconds = eatingSeconds;
        this.forksCount = forksCount;
    }

    public void eatSpaghettiOnTable(Table table) {
        if (forksCount < MINIMUM_NUMBER_OF_FORKS_TO_EAT)
            table.preemptFork();
        eat();
        putForksToTable(table);
    }

    private void putForksToTable(Table table) {
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
