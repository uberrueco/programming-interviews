package com.ulises.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ulises.utills.Utils.printMsg;

public class Subsets {

    public List<List<Integer>> subsetsNoDups(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        subsNoDups(results, new ArrayList<>(), nums, 0, 0);
        return results;
    }

    private void subsNoDups(List<List<Integer>> results, List<Integer> temp, int[] nums, int start, int recursionLevel) {
//        printMsg(String.format("Temp list %s", temp), recursionLevel);
        results.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            subsNoDups(results, temp, nums, i + 1, recursionLevel + 1);
            temp.remove(temp.size() - 1);
//            printMsg(String.format("Removed %s", temp), recursionLevel);
        }
    }

    public List<List<Integer>> subsetsWithDups(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        subsWithDups(results, new ArrayList<>(), nums, 0, 0);
        return results;
    }

    private void subsWithDups(List<List<Integer>> results, List<Integer> temp, int[] nums, int start, int recursionLevel) {
//        printMsg(String.format("Temp list %s", temp), recursionLevel);
        results.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates.
            temp.add(nums[i]);
            subsWithDups(results, temp, nums, i + 1, recursionLevel + 1);
            temp.remove(temp.size() - 1);
//            printMsg(String.format("Removed %s", temp), recursionLevel);
        }
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsetsNoDups(new int[]{1, 2, 3}));
        System.out.println(subsets.subsetsWithDups(new int[]{1, 2, 2, 3}));
    }
}
