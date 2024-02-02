package me.java.concurrency;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class PhilosopherTest {

    @Test
    void take_the_fork_from_the_table_if_have_less_than_one_fork() {
        Table table = new Table(1);
        Philosopher sut = getPhilosopherByForksCount(1);

        assertDoesNotThrow(() -> sut.eatSpaghettiOnTable(table));
        assertThat(sut.hasEaten()).isTrue();
    }

    private Philosopher getPhilosopherByForksCount(int forksCount) {
        return new Philosopher(1, forksCount);
    }

    @Test
    void cannot_eat_if_do_not_have_enough_forks_and_fail_to_get_a_fork_from_the_table() {
        Table table = new Table(0);
        Philosopher sut = getPhilosopherByForksCount(1);

        assertThatThrownBy(() -> sut.eatSpaghettiOnTable(table))
                .hasMessageContaining("There is no forks.");
        assertThat(sut.hasEaten()).isFalse();
    }

    @Test
    void do_not_take_a_fork_from_the_table_if_you_have_enough_forks() {
        Table table = new Table(0);
        Philosopher sut = getPhilosopherByForksCount(2);

        assertDoesNotThrow(() -> sut.eatSpaghettiOnTable(table));
        assertThat(sut.hasEaten()).isTrue();
    }

    @Test
    void put_all_forks_after_eating_if_have_two_or_more_forks() {
        Table table = new Table(0);
        Philosopher sut = getPhilosopherByForksCount(2);

        sut.eatSpaghettiOnTable(table);

        assertThat(table.getForksCount()).isEqualTo(2);
        assertThat(sut.getForksCount()).isZero();
        assertThat(sut.hasEaten()).isTrue();
    }

    @Test
    void it_takes_time_to_eat() {
        Table table = new Table(0);
        int eatingSeconds = 1;
        Philosopher sut = new Philosopher(eatingSeconds, 2);
        long startTime = System.currentTimeMillis();

        sut.eatSpaghettiOnTable(table);
        long endTime = System.currentTimeMillis();

        long timeTaken = endTime - startTime;
        long eatingMilliSeconds = eatingSeconds * 1000L;
        assertThat(eatingMilliSeconds).isLessThanOrEqualTo(timeTaken);
    }

}
