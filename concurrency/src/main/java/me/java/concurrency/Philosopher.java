package me.java.concurrency;

class Philosopher {
    private int forksCount;
    private boolean eaten = false;

    Philosopher(int forksCount) {
        this.forksCount = forksCount;
    }

    public void eatSpaghettiOnTable(Table table) {
        if (forksCount < 2)
            table.preemptFork();
        eaten = true;
        table.addForks(forksCount);
    }

    int getForksCount() {
        return forksCount;
    }

    boolean hasEaten() {
        return eaten;
    }
}
