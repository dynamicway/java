package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.stream.IntStream;

class CreateFluxTest {

    @Test
    void create_Flux_by_range() {
        int since = 0;
        int until = 100;
        Flux<Integer> givenFluxByRange = Flux.range(since, until);

        Integer[] expectedDataOfFlux = IntStream.range(since, until).boxed().toArray(Integer[]::new);

        StepVerifier.create(givenFluxByRange)
                .expectNext(expectedDataOfFlux)
                .verifyComplete();
    }

    @Test
    void create_Flux_by_just() {
        Integer[] data = {1, 2, 3};
        Flux<Integer> givenFluxByJust = Flux.just(data);

        StepVerifier.create(givenFluxByJust)
                .expectNext(data)
                .verifyComplete();
    }

}
