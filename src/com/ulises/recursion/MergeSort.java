package com.ulises.recursion;

import java.util.Arrays;

public class MergeSort {

    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = sort(Arrays.copyOfRange(array, 0, mid));
        int[] right = sort(Arrays.copyOfRange(array, mid, array.length));
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int l = 0; int r = 0;
        for (int sorted  = 0; sorted < merged.length; sorted++) {
            if ((l < left.length && r < right.length && left[l] < right[r]) || r >= right.length) {
                merged[sorted] = left[l++];
            } else {
                merged[sorted] = right[r++];
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 4, 1, 7, 2};
        MergeSort sort = new MergeSort();
        int[] sorted = sort.sort(array);
        for (int a : sorted)
            System.out.print(a + ",");
    }
}
