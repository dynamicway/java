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

    @Test
    void when_modifying_objects_then_original_objects_changed() {
        Person givenPerson = new Person("Origin");

        changeName(givenPerson);

        assertThat(givenPerson.getName()).isEqualTo("Changed");
    }

    private void changeName(final Person person) {
        person.setName("Changed");
    }

    private static class Person {
        private String name;

        public Person(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }
    }

}
