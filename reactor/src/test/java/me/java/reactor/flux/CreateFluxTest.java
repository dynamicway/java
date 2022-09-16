package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CreateFluxTest {

    @Test
    void create_Flux_by_range() {
        int since = 0;
        int until = 100;
        Flux<Integer> givenFluxByRange = Flux.range(since, until);

        Integer[] expectedDataOfFlux = IntStream.range(since, until)
                .boxed()
                .toArray(Integer[]::new);

        StepVerifier.create(givenFluxByRange)
                .expectNext(expectedDataOfFlux)
                .verifyComplete();
    }

    @Test
    void create_Flux_by_generate() {

        ArrayList<Integer> list = new ArrayList<>();

        Consumer<SynchronousSink<Integer>> synchronousSinkConsumer = new Consumer<>() {

            private int i = 0;

            @Override
            public void accept(SynchronousSink<Integer> integerSynchronousSink) {
                integerSynchronousSink.next(i++);
                if (i == 10)
                    integerSynchronousSink.complete();
            }

        };

        Flux.generate(synchronousSinkConsumer).subscribe(list::add);

        List<Integer> result = IntStream.range(0, 10).boxed().toList();

        assertThat(list).containsExactlyElementsOf(result);
    }

}
