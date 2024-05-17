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

    @CsvSource({
            "1,I",
            "2,II",
            "3,III",
            "4,IV",
            "5,V",
            "6,VI",
            "7,VII",
            "8,VIII",
            "9,IX",
            "10,X",
            "11,XI",
            "12,XII",
            "13,XIII",
            "14,XIV",
            "15,XV",
            "16,XVI",
            "17,XVII",
            "18,XVIII",
            "19,XIX",
            "20,XX",
            "40,XL",
            "42,XLII",
            "999,CMXCIX",
            "1999,MCMXCIX"
    })
    @ParameterizedTest
    void encode(int arabic, String roman) {
        assertThat(Roman.encode(arabic)).isEqualTo(roman);
    }
}
