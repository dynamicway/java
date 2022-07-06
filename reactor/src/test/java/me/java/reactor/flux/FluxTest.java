package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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

    @Test
    void multiple_subscribe_to_a_single_flux() {
        List<String> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux<Integer> flux = Flux.just(integers);
        flux.subscribe(integer -> list.add("subscribe1" + integer));
        flux.subscribe(integer -> list.add("subscribe2" + integer));

        assertThat(list).containsExactly(
                "subscribe1" + 1,
                "subscribe1" + 2,
                "subscribe1" + 3,
                "subscribe2" + 1,
                "subscribe2" + 2,
                "subscribe2" + 3
        );
    }

    @Test
    void if_nothing_in_the_onSubscribe_then_nothing_happens() {
        List<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux.just(integers).subscribe(new Subscriber<>() {

            @Override
            public void onSubscribe(Subscription s) {
            }

            @Override
            public void onNext(Integer integer) {
                list.add(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
            }

        });

        assertThat(list).isEmpty();
    }

    @Test
    void onNext_is_executed_when_onSubscribe_call_request_in_Subscription() {
        List<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux.just(integers).subscribe(new Subscriber<>() {

            @Override
            public void onSubscribe(Subscription s) {
                s.request(3);
            }

            @Override
            public void onNext(Integer integer) {
                list.add(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
            }

        });

        assertThat(list).containsExactly(integers);
    }

    @Test
    void if_onNext_does_not_call_request_then_stream_ends() {
        List<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3};
        Flux.just(integers).subscribe(new Subscriber<>() {

            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                list.add(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
            }

        });

        assertThat(list).containsExactly(integers[0]);
    }

}
