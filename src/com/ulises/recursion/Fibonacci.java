package com.ulises.recursion;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Fibonacci {

    static Map<Integer, Integer> map = new HashMap<>();

    public static int fib (int n) {
        if (n == 0) {
            map.put(0, 0);
        }
        if (n == 1) {
            map.put(1, 1);
        }
        if (map.containsKey(n)) return map.get(n);
        map.put(n, fib(n - 1) + fib(n - 2));
        return map.get(n);
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
    }
}
