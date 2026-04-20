package com.techcookies;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Functional {
    public static void main(String[] args) {
        printOddNumbers(List.of(1,2,44,22,23, 13, 3, 12));
        //printListOfString(List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Azure", "Docker", "Kubernetes"));
        printListOfStringOfLength(List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Azure", "Docker", "Kubernetes"), 4);
        int sum = reduceFuction(List.of(1,2,44,22,23, 13, 2, 12));
        System.out.println("Sum "+ sum);

        //Sort
        List.of(1,2,44,22,23, 13, 2, 12).stream()
                .sorted()
                .forEach(System.out::println);
        List.of(1,2,44,22,23, 13, 2, 12).stream()
                .sorted(Comparator.reverseOrder()) // Comparator.naturalOrder()
                .forEach(System.out::println);
        List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Azure", "Docker", "Kubernetes")
                .stream()
                .sorted(Comparator.comparing(str -> str.length()))
                .forEach(System.out::println);
        //Collecting
        System.out.println("Collecting---");
        List.of(1,2,44,22,23, 13, 2, 12)
                .stream()
                .map(num -> num * num)
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    private static void printOddNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);
    }

    private static void printListOfString(List<String> strs){
        strs.stream()
                .filter(str -> str.contains("Spring"))
                .forEach(System.out::println);
    }

    private static void printListOfStringOfLength(List<String> strs, Integer len){
        strs.stream()
                .filter(str -> str.length() >= len)
                .forEach(System.out::println);
    }

    private static int print(int a, int b){
        return a + b;
    }
    private static int reduceFuction(List<Integer> num){
        return num.stream()
                .reduce(0, Functional::print);

    }
}
