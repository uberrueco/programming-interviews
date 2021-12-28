package com.ulises.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        helper(combs, new ArrayList<>(), 1, n, k);
        return combs;
    }
    public static void helper(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if (k == 0) {
            combs.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {
            comb.add(i);
            helper(combs, comb, i + 1, n, k - 1);
            comb.remove(comb.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinations(5, 2));
    }
}
