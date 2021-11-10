package com.ulises.utills;

public class Utils {

    public static void printMsg(String s, int recursionLevel) {
        for (int i = 0; i <= recursionLevel; i++) {
            System.out.print("-");
        }
        System.out.println(s);
    }
}
