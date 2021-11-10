package com.ulises.multithreading;

public class WaitAndJoin {

    public static int COUNTER = 0;

    public static synchronized void increment() {
        COUNTER++;
    }

    public static void main(String arg[]) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(COUNTER);
    }
}
