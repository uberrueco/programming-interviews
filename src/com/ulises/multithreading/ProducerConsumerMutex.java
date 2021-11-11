package com.ulises.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class has a mutex object of type Lock and condition variables to signal other threads.
 */
public class ProducerConsumerMutex<E> {

    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();
    private static final int MAX_SIZE = 15;

    Queue<E> queue = new LinkedList<>();

    public void put(E e) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == MAX_SIZE) {
                notFull.await();
            }
            queue.offer(e);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                notEmpty.wait();
            }
            E e = queue.poll();
            notEmpty.signalAll();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
