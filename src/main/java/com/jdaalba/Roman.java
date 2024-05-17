package com.jdaalba;

import java.util.LinkedHashMap;
import java.util.Map;

public class Roman {
    static final Map<String, Integer> NUMBERS;

    static {
        NUMBERS = new LinkedHashMap<>();
        NUMBERS.put("M", 1_000);
        NUMBERS.put("CM", 900);
        NUMBERS.put("D", 500);
        NUMBERS.put("CD", 400);
        NUMBERS.put("C", 100);
        NUMBERS.put("XC", 90);
        NUMBERS.put("L", 50);
        NUMBERS.put("XL", 40);
        NUMBERS.put("X", 10);
        NUMBERS.put("IX", 9);
        NUMBERS.put("V", 5);
        NUMBERS.put("IV", 4);
        NUMBERS.put("I", 1);
    }

    public static int decode(String number) {
        for (var entry : NUMBERS.entrySet()) {
            if (number.startsWith(entry.getKey())) {
                return entry.getValue() + decode(getTail(number, entry.getKey().length()));
            }
        }
        return 0;
    }

    private static String getTail(String number, int n) {
        return number.length() > 1 ? number.substring(n) : "";
    }

    public static String encode(int number) {
        throw new UnsupportedOperationException("unimplemented");
    }
}
