package com.ulises.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, results, new ArrayList<>(), 0, target);
        return results;
    }

    private void helper(int[] nums, List<List<Integer>> results, List<Integer> temp, int index, int remain) {
        if (remain < 0) return;
        if (remain == 0) {
            results.add(new ArrayList<>(temp));
        } else {
            for (int i = index; i < nums.length; i++) {
                temp.add(nums[i]);
                helper(nums, results, temp, i, remain - nums[i]);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        List<List<Integer>> l  = c.combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println(l);
    }
}
