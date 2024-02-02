package me.java.concurrency;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableTest {

    @Test
    void preemption_fails_if_there_is_no_fork() {
        Table sut = new Table(2);

        sut.preemptFork();
        sut.preemptFork();
        assertThatThrownBy(sut::preemptFork)
                .hasMessageContaining("There is no forks.");
    }

}
