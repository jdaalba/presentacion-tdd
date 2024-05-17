package com.jdaalba;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Roman {
    static final Map<String, Integer> NUMBERS;

    static {
        var map = new LinkedHashMap<String, Integer>();
        map.put("M", 1_000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
        NUMBERS = Collections.unmodifiableMap(map);
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
        for (var entry : NUMBERS.entrySet()) {
            if (number >= entry.getValue()) {
                return entry.getKey() + encode(number - entry.getValue());
            }
        }
        return "";
    }
}
