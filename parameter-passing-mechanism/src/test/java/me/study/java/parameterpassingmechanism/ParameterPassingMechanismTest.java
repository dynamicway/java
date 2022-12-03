package me.study.java.parameterpassingmechanism;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParameterPassingMechanismTest {

    @Test
    void when_modifying_primitives_then_original_values_not_modified() {
        int originValue = 10;

        modifyValue(originValue);

        assertThat(originValue).isEqualTo(10);
    }

    private void modifyValue(int originValue) {
        originValue = 20;
    }

}
