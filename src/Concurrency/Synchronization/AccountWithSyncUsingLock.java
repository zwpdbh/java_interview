package Concurrency.Synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    account.deposit(1);
//                }
//            });
            executorService.execute(() -> {
                account.deposit(1);
            });
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {}
        System.out.println("The balance is : " + account.getBalance());
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
}
