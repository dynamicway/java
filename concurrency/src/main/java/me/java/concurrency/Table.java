package me.java.concurrency;

class Table {
    private int forksCount;

    Table(int forksCount) {
        this.forksCount = forksCount;
    }

    void addForks(int forkCount) {
        this.forksCount = forkCount;
    }

    void preemptFork() {
        if (forksCount == 0)
            throw new RuntimeException("There is no forks.");
        forksCount--;
    }

    int getForksCount() {
        return forksCount;
    }
}
