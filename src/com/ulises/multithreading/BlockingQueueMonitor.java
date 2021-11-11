package com.ulises.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueMonitor<T> {

    private Queue<T> queue;
    private int capacity;

    public BlockingQueueMonitor(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while(queue.size() == capacity) {
           wait();
        }
        queue.offer(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }
        T e = queue.poll();
        notifyAll();
        return e;
    }
}
