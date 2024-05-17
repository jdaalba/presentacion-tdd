package com.jdaalba;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanTest {

    @CsvSource({
            "I,1",
            "II,2",
            "III,3",
            "IV,4",
            "V,5",
            "VI,6",
            "VII,7",
            "VIII,8",
            "IX,9",
            "X,10",
            "XIII,13",
            "XL,40",
            "XLII,42",
            "CMXCIX,999",
            "MCMXCIX,1999"
    })
    @ParameterizedTest
    void decode(String roman, int expected) {
        assertThat(Roman.decode(roman)).isEqualTo(expected);
    }
}
