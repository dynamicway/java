package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

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

}
