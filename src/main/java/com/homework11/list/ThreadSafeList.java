package main.java.com.homework11.list;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<E> extends CopyOnWriteArrayList<E> {
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private Lock readLock;
    private Lock writeLock;


    private int fuelTime = (int)(Math.random() * 10) + 3;

    public ThreadSafeList() {
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    @Override
    public E get(int index) {
        readLock.lock();
        try{
            return super.get(index);

        } finally{
            readLock.unlock();

        }

    }

    @Override
    public void add(int index, E element) {
        writeLock.lock();
        try{
            wait(fuelTime);
            super.add(index, element);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();

        }


    }

    @Override
    public E remove(int index) {
        readLock.lock();
        try{
            return super.remove(index);

        } finally {
            readLock.unlock();

        }

    }

}
