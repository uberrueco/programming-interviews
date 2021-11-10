package com.ulises.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerSync<E> {

    private static final int MAX_SIZE = 15;
    Object notEmpty = new Object();
    Object notFull = new Object();

    Queue<E> queue = new LinkedList<>();

    public synchronized void put(E e) throws InterruptedException {
        while(queue.size() == MAX_SIZE) {
            notFull.wait();
        }
        queue.offer(e);
        notFull.notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while(queue.isEmpty()) {
            notEmpty.wait();
        }
        E e = queue.poll();
        notEmpty.notifyAll();
        return e;
    }
}
