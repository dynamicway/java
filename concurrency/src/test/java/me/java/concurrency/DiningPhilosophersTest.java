package me.java.concurrency;

import org.junit.jupiter.api.Test;

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
    }

}
