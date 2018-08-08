package Concurrency.Synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCooperation {
    private static Account account = new Account();

    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("Thread 1\t\tThread 2\t\t\tBalance");

        executorService.execute(() -> {
            try {
                while (true) {
                    account.deposite((int)(Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException ex) {

            }
        });

        executorService.execute(() -> {
            while (true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        });
        executorService.shutdown();

    }


    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition newDeposite = lock.newCondition();


        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println("\t\t\tWait for a deposit");
                    newDeposite.await();
                }
                balance -= amount;
                System.out.println("\t\t\twithdraw " + amount + "\t\t\t\t" + getBalance());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void deposite(int amount) {
            lock.lock();;
            try {
                balance += amount;
                System.out.println("Deposite " + amount + "\t\t\t\t\t\t\t" + getBalance());

                // Signal thread waiting on the condition
                newDeposite.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
