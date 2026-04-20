package com.techcookies;

import java.util.List;

public class BehaviorParams {
    public static void main(String[] args) {
        List.of(1, 2, 44, 22, 23, 13, 3, 12).stream()
                .filter(x-> x% 2 == 0)
                .forEach(System.out::println);

    }
}
