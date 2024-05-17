package com.jdaalba;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    @CsvSource({
            "1,1",
            "2,2",
            "3,Fizz!",
            "5,Buzz!",
            "6,Fizz!",
            "9,Fizz!",
            "10,Buzz!",
            "15,FizzBuzz!",
            "30,FizzBuzz!"
    })
    @ParameterizedTest
    void apply(int input, String expected) {
        assertThat(FizzBuzz.apply(input)).isEqualTo(expected);
    }
}
