package com.ulises.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ParenthesisCombinations {

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        helper(results, new StringBuilder(), 0, 0 , n);
        return results;
    }

    private void helper(List<String> results, StringBuilder builder, int open, int close, int n) {

        if (builder.length() == n * 2) {
            results.add(builder.toString());
            return;
        }

        if (open < n) {
            builder.append("(");
            helper(results, builder, open + 1, close, n);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (close < open) {
            builder.append(")");
            helper(results, builder, open, close + 1, n);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main (String[] args) {
        ParenthesisCombinations combinations = new ParenthesisCombinations();
        System.out.println(combinations.generateParenthesis(3));
    }
}
