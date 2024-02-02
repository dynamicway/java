package me.java.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TableTest {

    @Test
    void preemption_fails_if_there_is_no_fork() {
        Table sut = new Table(2);

        sut.preemptFork();
        sut.preemptFork();
        assertThatThrownBy(sut::preemptFork)
                .hasMessageContaining("There is no forks.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    void if_a_fork_is_added_it_can_be_preempted_again(int addedForksCount) {
        Table sut = new Table(1);

        sut.preemptFork();
        assertThatThrownBy(sut::preemptFork)
                .hasMessageContaining("There is no forks.");
        sut.addForks(addedForksCount);
        for (int i = 0; i < addedForksCount; i++) {
            assertDoesNotThrow(sut::preemptFork);
        }
    }

}
