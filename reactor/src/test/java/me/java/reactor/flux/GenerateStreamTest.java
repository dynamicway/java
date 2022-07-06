package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class GenerateStreamTest {

    @Test
    void justOrEmpty_arguments_can_be_null() {
        Mono.justOrEmpty(null)
                .subscribe();
    }

    @Test
    void just_throws_NullPointerException_when_argument_is_null() {
        assertThatCode(() -> Mono.just(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void range_in_Flux() {
        List<Integer> list = new ArrayList<>();

        int start = 0;
        int count = 100;
        Flux.range(start, count)
                .subscribe(list::add);

        List<Integer> result = IntStream.range(start, count)
                .boxed()
                .toList();

        assertThat(list).containsExactlyElementsOf(result);
    }

    @Test
    void generate_in_Flux() {

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
