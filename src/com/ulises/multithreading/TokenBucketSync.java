package com.ulises.multithreading;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TokenBucketSync {

    private int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketSync(int max) {
        this.MAX_TOKENS = max;
    }

    public synchronized void getToken() throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;
        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }
        if (possibleTokens == 0) {
            System.out.println("No tokens");
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }
        lastRequestTime = System.currentTimeMillis();

        System.out.println("Yoink! by: " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
    }

    public static void main(String[] args) {
        TokenBucketSync bucket = new TokenBucketSync(10);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 20; i++) {
            executor.submit(new Task(bucket));
        }
    }

    public static class Task<T> implements Runnable {

        private TokenBucketSync bucket;

        public Task(TokenBucketSync bucket) {
            this.bucket = bucket;
        }

        @Override
        public void run() {
            Random r = new Random();
            while(true) {
                try {
                    bucket.getToken();
                    Thread.sleep(r.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
