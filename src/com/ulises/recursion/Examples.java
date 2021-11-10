package com.ulises.recursion;

import com.ulises.lists.List;
import com.ulises.lists.ListNode;

public class Examples {

    public static void main(String args[]) {
        printReverseRecursive(0, "Hola".toCharArray());
    }

    public static void printReverseRecursive(int index, char[] arr) {
        if (arr == null || index >= arr.length) return;
        printReverseRecursive(index + 1, arr);
        System.out.print(arr[index]);
    }

    public static void reverseInPlaceRecursive(int l, char[] arr, int r) {
        if (l >= r) return;
        char c = arr[l];
        arr[l] = arr[r];
        arr[r] = c;
        reverseInPlaceRecursive(l + 1, arr, r - 1);
    }
}
