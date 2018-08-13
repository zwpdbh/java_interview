package Concurrency.Synchronization;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {
    private static Account account = new Account();
    private static AnotherAccountUsingSemaphore anotherAccountUsingSemaphore
            = new AnotherAccountUsingSemaphore();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                account.deposit(1);
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {}

        System.out.println("The balance is : " + account.getBalance());


        /**use semaphore instead of lock*/
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                anotherAccountUsingSemaphore.deposite(1);
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}

        System.out.println("The balance is : " + anotherAccountUsingSemaphore.getBalance());
    }

    public static class Account {
        private static Lock lock = new ReentrantLock();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            lock.lock();;
            try {
                int newBalance = balance + amount;

                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ex) {

            } finally {
                lock.unlock();
            }
        }
    }
    /**A semaphore with just one permit can be used to simulate a mutually exclusive lock*/
    public static class AnotherAccountUsingSemaphore {
        private static Semaphore semaphore = new Semaphore(1);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposite(int amount) {
            try {
                semaphore.acquire();
                int newBalance = balance + amount;

                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
