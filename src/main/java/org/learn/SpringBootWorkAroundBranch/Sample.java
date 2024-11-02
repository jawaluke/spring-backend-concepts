package org.learn.SpringBootWorkAroundBranch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Sample {
    public static void main(String[] args) {

        List<String> lis = Arrays.asList("apple", "banana", "apricot", "cherry", "avocado");
        lis.stream()
                .filter(e -> e.startsWith("a", 0))
                .collect(
                        Collectors.toList()
                )
                .stream()
                .forEach(System.out::println);
    }
}
