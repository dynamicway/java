package me.java.designpattern.creation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonObjectTest {

    @Test
    void cannot_vouch_for_a_singleton_when_not_solved_concurrency() throws InterruptedException {
        int numberOfThreads = 2;
        List<SingletonObject> singletonObjects = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                singletonObjects.add(SingletonObject.getInstanceWithoutConcurrency());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        assertThat(singletonObjects).hasSize(numberOfThreads);
        assertThat(singletonObjects.get(0)).isNotSameAs(singletonObjects.get(1));
    }

    @Test
    void singleton_guarantee_through_synchronized() throws InterruptedException {
        List<SingletonObject> singletonObjects = Collections.synchronizedList(new ArrayList<>());
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                singletonObjects.add(SingletonObject.getInstanceWithSynchronized());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        assertThat(singletonObjects).hasSize(numberOfThreads);
        for (int first = 0; first < numberOfThreads; first++) {
            for (int second = 0; second < numberOfThreads; second++) {
                if (first == second)
                    continue;
                assertThat(singletonObjects.get(first)).isSameAs(singletonObjects.get(second));
            }
        }
    }

}
