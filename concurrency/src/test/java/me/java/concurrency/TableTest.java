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

    @Test
    void if_a_fork_is_added_it_can_be_preempted_again() {
        Table sut = new Table(1);

        sut.preemptFork();
        assertThatThrownBy(sut::preemptFork)
                .hasMessageContaining("There is no forks.");
        sut.putFork();
        sut.preemptFork();
    }

}
