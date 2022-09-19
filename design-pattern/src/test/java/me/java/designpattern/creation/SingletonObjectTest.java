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
        int numberOfThreads = 100;
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
        verifyNotSingleton(singletonObjects, numberOfThreads);
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
        verifySingleton(singletonObjects, numberOfThreads);
    }

    @Test
    void singleton_guarantee_through_holder() throws InterruptedException {
        List<SingletonObject> singletonObjects = Collections.synchronizedList(new ArrayList<>());
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                singletonObjects.add(SingletonObject.getInstanceWithHolder());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        assertThat(singletonObjects).hasSize(numberOfThreads);
        verifySingleton(singletonObjects, numberOfThreads);
    }

    private void verifySingleton(List<SingletonObject> singletonObjects, int numberOfThreads) {
        for (int first = 0; first < numberOfThreads; first++) {
            for (int second = 0; second < numberOfThreads; second++) {
                if (first == second)
                    continue;
                assertThat(singletonObjects.get(first)).isSameAs(singletonObjects.get(second));
            }
        }
    }

    private void verifyNotSingleton(List<SingletonObject> singletonObjects, int numberOfThreads) {
        boolean notSingleton = false;
        for (int first = 0; first < numberOfThreads; first++) {
            for (int second = 0; second < numberOfThreads; second++) {
                if (first == second)
                    continue;
                if (singletonObjects.get(first) != singletonObjects.get(second)) {
                    notSingleton = true;
                    break;
                }
            }
        }
        assertThat(notSingleton).isTrue();
    }

}
