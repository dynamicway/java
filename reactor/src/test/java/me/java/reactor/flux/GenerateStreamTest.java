package me.java.reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

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


}
