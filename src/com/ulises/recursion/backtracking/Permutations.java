package com.ulises.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

import static com.ulises.utills.Utils.printMsg;

public class Permutations {

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        perm1(0, nums, visited, new ArrayList(), output);
        return output;
    }

    private void perm1(int index, int[] nums, boolean[] visited, List<Integer> result, List<List<Integer>> output) {
        if (index == nums.length) {
            output.add(List.copyOf(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) { // Equivalent to !result.contains(nums[i])
                result.add(nums[i]);
                visited[i] = true;
                perm1(index + 1, nums, visited, result, output);
                result.remove(result.size() - 1); // Backtracking
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        perm2(results, new ArrayList<>(), nums, 0);
        return results;
    }

    private void perm2(List<List<Integer>> results, List<Integer> temp, int[] nums, int recursionLevel) {
        if (temp.size() == nums.length) {
            results.add(new ArrayList<>(temp));
            // printMsg(String.format("Reached end %s", results), recursionLevel);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
//                printMsg(String.format("Temp %s ", temp), recursionLevel);
                perm2(results, temp, nums, recursionLevel + 1);
                temp.remove(temp.size() - 1);
//                printMsg(String.format("Removed %s ", temp), recursionLevel);
            }
        }
    }

    public static void main(String[] args) {
       Permutations bt = new Permutations();
       List<List<Integer>> permutations1 = bt.permute1(new int[] {1, 2, 3});
       System.out.println(permutations1);
       List<List<Integer>> permutations2 = bt.permute2(new int[] {1, 2, 3});
       System.out.println(permutations2);
    }
}
