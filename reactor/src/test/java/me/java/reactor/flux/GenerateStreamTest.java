package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class GenerateStreamTest {

    @Test
    void justOrEmpty_arguments_can_be_null() {
        Mono.justOrEmpty(null)
                .subscribe();
    }

}
