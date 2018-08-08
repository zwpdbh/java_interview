package Concurrency.Synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                // if we don't use synchronized lock, the result is not correct
                synchronized (account) {
                    account.deposit(1);
                }
            });
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {}

        System.out.println("The balance is : " + account.getBalance());
    }


    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }


        public void deposit(int amount) {
            int newBlance = balance + amount;
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {

            }
            balance = newBlance;
        }
    }
}
