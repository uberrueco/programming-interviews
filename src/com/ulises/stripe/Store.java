package com.ulises.stripe;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Store {

    public static int compute_penalty(String log, int closingTime) {
        if (log.isEmpty()) {
            return 0;
        }
        String[] strLog = log.split(" ");
        int penalty = 0;
        for (int i = 0; i < closingTime; i++) {
            if (strLog[i].equals("N")) {
                penalty++;
            }
        }
        for (int i = closingTime; i <strLog.length; i++) {
            if (strLog[i].equals("Y")) {
                penalty++;
            }
        }
        return penalty;
    }

    static class Time {
        int hour;
        int penalty;

        public Time(int hour, int penalty) {
            this.hour = hour;
            this.penalty = penalty;
        }
    }

    public static int find_best_closing_time(String log) {

        PriorityQueue<Time> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.penalty < o2.penalty) {
                return -1;
            } else  if (o1.penalty > o2.penalty) {
                return 1;
            }
            return 0;
        });

        for (int i = 0; i <= log.split(" ").length; i++) {

            int penalty = compute_penalty(log, i);
            queue.offer(new Time(i, penalty));
        }

        return queue.poll().hour;
    }

    public static void main(String[] args) {
        assertEquals(3, compute_penalty("Y Y N Y", 0));
        assertEquals(2, compute_penalty("N Y N Y", 2));
        assertEquals(1, compute_penalty("Y Y N Y", 4));
        assertEquals(0, compute_penalty("", 0));
        assertEquals(0, compute_penalty(" ", 0));
        assertEquals(0, compute_penalty(" N", 0));

        assertEquals(3, compute_penalty("Y Y Y N N N N", 0));
        assertEquals(4, compute_penalty("Y Y Y N N N N", 7));
        assertEquals(0, compute_penalty("Y Y Y N N N N", 3));
        assertEquals(0, compute_penalty("", 0));
        assertEquals(1, compute_penalty("Y N Y N N N N", 3));

        assertEquals(2, find_best_closing_time("Y Y N N"));

        assertEquals(3, find_best_closing_time("Y Y Y N N N N"));
        assertEquals(0, find_best_closing_time(""));
        assertEquals(0, find_best_closing_time("N N N N"));
        assertEquals(4, find_best_closing_time("Y Y Y Y"));
        assertEquals(5, find_best_closing_time("N Y Y Y Y N N N Y N N Y Y N N N N Y Y N N Y N N N"));
        assertEquals(0, find_best_closing_time("N N N N N Y Y Y N N N N Y Y Y N N N Y N Y Y N Y N"));
        assertEquals(25, find_best_closing_time("Y Y N N N Y Y N Y Y N N N Y Y N N Y Y Y N Y N Y Y"));
    }

    public static <T> void assertEquals(T expected, T actual) {
        if (expected == null && actual == null || actual != null && actual.equals(expected)) {
            System.out.println("PASSED");
        } else {
            throw new AssertionError("Expected:\n  " + expected + "\nActual:\n  " + actual + "\n");
        }
    }
}
