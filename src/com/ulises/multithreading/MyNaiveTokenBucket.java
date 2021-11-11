package com.ulises.multithreading;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This was my "naive" solution according to https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/qVl160R3xjk
 *  @see TokenBucketMonitor
 */
public class MyNaiveTokenBucket<T> {

    int MAX_CAPACITY = 10;
    int bucket;

    private synchronized void incrementBucket() throws InterruptedException {
        while(bucket == MAX_CAPACITY) {
            System.out.println("Bucket full.");
            wait();
        }
        bucket++;
        notifyAll();
    }

    public MyNaiveTokenBucket() {
        bucket = 0;
        Thread refill = new Thread(() -> {
            try {
                while(true) {
                    incrementBucket();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        refill.setDaemon(true);
        refill.start();
    }

    public synchronized T getToken() throws InterruptedException {
        while(bucket == 0) {
            System.out.println("No tokens");
            wait();
        }
        System.out.println("Yoink! left:" + --bucket);
        notifyAll();
        return null;
    }

    public static void main(String[] args) {
        MyNaiveTokenBucket bucket = new MyNaiveTokenBucket();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 2; i++) {
            executor.submit(new Task(bucket));
        }
    }

    public static class Task<T> implements Runnable {

        private MyNaiveTokenBucket<T> bucket;

        public Task(MyNaiveTokenBucket<T> bucket) {
            this.bucket = bucket;
        }

        @Override
        public void run() {
            Random r = new Random();
            while(true) {
                try {
                    bucket.getToken();
                    System.out.println("Got token by " + Thread.currentThread().getName());
                    Thread.sleep(r.nextInt(50000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
