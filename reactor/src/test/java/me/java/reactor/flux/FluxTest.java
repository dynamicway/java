package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxTest {

    @Test
    void subscribe() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux.just(integers)
                .subscribe(list::add);

        assertThat(list).containsExactly(integers);
    }

    @Test
    void when_subscribe_calls_onNext_then_executed_doOnNext() {
        List<String> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux<Integer> flux = Flux.just(integers)
                .doOnNext(integer -> list.add("doOnNext" + integer));

        flux.subscribe(integer -> list.add("subscribe" + integer));

        assertThat(list).containsExactly(
                "doOnNext" + 1,
                "subscribe" + 1,
                "doOnNext" + 2,
                "subscribe" + 2,
                "doOnNext" + 3,
                "subscribe" + 3
        );
    }

    @Test
    void doOnNext_is_not_executed_when_flux_has_no_subscribe() {
        List<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux<Integer> flux = Flux.just(integers)
                .doOnNext(list::add);

        assertThat(list).isEmpty();
    }

}
