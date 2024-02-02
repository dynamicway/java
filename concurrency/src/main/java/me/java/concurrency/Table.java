package me.java.concurrency;

class Table {
    private int forkCount;

    Table(int forkCount) {
        this.forkCount = forkCount;
    }

    void putFork() {
        forkCount++;
    }

    void preemptFork() {
        if (forkCount == 0)
            throw new RuntimeException("There is no forks.");
        forkCount--;
    }
}
