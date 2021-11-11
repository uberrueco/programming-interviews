package com.ulises.multithreading;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackLockExecutor {

    Lock lock = new ReentrantLock();
    Condition newCall = lock.newCondition();

    static class Callback {
        long executeAt;
        String message;

        public Callback(long executeAt, String message) {
            this.executeAt = System.currentTimeMillis() + executeAt * 1000;
            this.message = message;
        }
    }

    private PriorityQueue<Callback> queue = new PriorityQueue<>(new Comparator<Callback>() {
        @Override
        public int compare(Callback o1, Callback o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });

    public void start() throws InterruptedException {
        long sleepFor;
        while(true) {
            lock.lock();
            while (queue.size() == 0) {
                newCall.await();
            }
            while(queue.size() != 0) {
                sleepFor = findSleepDuration();

                if(sleepFor <=0)
                    break;

                newCall.await(sleepFor, TimeUnit.MILLISECONDS);
            }
            Callback cb = queue.poll();
            System.out.println("Executed at " + System.currentTimeMillis() / 1000 + " required at " + cb.executeAt / 1000
                    + ": message:" + cb.message);;
        }
    }

    public void registerCallback(Callback callback) {
        try {
            lock.lock();
            queue.offer(callback);
            newCall.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return queue.peek().executeAt - currentTime;
    }

    public static void runTestTenCallbacks() throws InterruptedException {
        Set<Thread> allThreads = new HashSet<Thread>();
        final DeferredCallbackLockExecutor deferredCallbackExecutor = new DeferredCallbackLockExecutor();

        Thread service = new Thread(() -> {
            try {
                deferredCallbackExecutor.start();
            } catch (InterruptedException ie) {

            }
        });

        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                Callback cb = new Callback(1, "Hello this is " + Thread.currentThread().getName());
                deferredCallbackExecutor.registerCallback(cb);
            });
            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep(1000);
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        runTestTenCallbacks();
    }
}
