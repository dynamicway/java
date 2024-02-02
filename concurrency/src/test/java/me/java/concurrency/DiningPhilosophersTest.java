package me.java.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DiningPhilosophersTest {

    @Test
    void everyone_can_finish_their_meal_if_there_are_enough_forks_on_the_table() {
        Table tableWithEnoughForks = new Table(99);
        List<Philosopher> philosophers = List.of(new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1));
        DiningPhilosophers sut = new DiningPhilosophers(2, tableWithEnoughForks, philosophers);

        sut.eat();

        assertThat(sut.hasEaten()).isTrue();
    }

    @Test
    void cannot_eat_after_meal_time() {
        Table tableWithEnoughForks = new Table(99);
        List<Philosopher> philosophers = List.of(new Philosopher(2, 1),
                                                 new Philosopher(2, 1),
                                                 new Philosopher(2, 1),
                                                 new Philosopher(2, 1),
                                                 new Philosopher(2, 1));
        DiningPhilosophers sut = new DiningPhilosophers(1, tableWithEnoughForks, philosophers);

        assertThatThrownBy(sut::eat)
                .hasMessageContaining("It takes too long to eat.");
        assertThat(sut.hasEaten()).isFalse();
    }

    @Test
    @Timeout(2)
    void philosophers_can_eat_at_the_same_time() {
        Table table = new Table(99);
        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            philosophers.add(new Philosopher(1, 1));
        }
        DiningPhilosophers sut = new DiningPhilosophers(2, table, philosophers);

        sut.eat();

        assertThat(sut.hasEaten()).isTrue();
    }

    @Test
    void you_cannot_eat_if_you_do_not_have_enough_forks() {
        Table table = new Table(0);
        List<Philosopher> philosophers = List.of(new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1),
                                                 new Philosopher(1, 1));
        DiningPhilosophers sut = new DiningPhilosophers(3, table, philosophers);

        assertThatThrownBy(sut::eat)
                .hasMessageContaining("It takes too long to eat.");
        assertThat(sut.hasEaten()).isFalse();
    }

}
