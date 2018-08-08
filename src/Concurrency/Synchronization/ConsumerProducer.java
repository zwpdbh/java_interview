package Concurrency.Synchronization;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // Producer
        executorService.execute(() -> {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes : " + i);
                    buffer.write(i++);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // Consumer
        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads : " + buffer.read());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    private static class Buffer {
        private static final int CAPACITY = 1;
        private LinkedList<Integer> queue = new LinkedList<>();

        // Create a new lock
        private static Lock lock = new ReentrantLock();

        // Create two conditions
        private static Condition untilNotEmpty = lock.newCondition();
        private static Condition untilNotFull = lock.newCondition();

        public void write(int value) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("Wait for untilNotFull condition");
                    untilNotFull.await();;
                }

                // add element at the tail
                queue.offer(value);
                untilNotEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("\t\t\tWait for untilNotEmpty condition");
                    untilNotEmpty.await();
                }
                value = queue.remove();
                untilNotFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }
}
