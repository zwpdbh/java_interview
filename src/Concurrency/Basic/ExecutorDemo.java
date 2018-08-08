package Concurrency.Basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new PrintChar('a', 100));
        executorService.execute(new PrintChar('b', 100));
        executorService.execute(new PrintNum(100));

        executorService.shutdown();
    }
}
