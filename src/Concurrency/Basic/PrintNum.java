package Concurrency.Basic;

class PrintNum implements Runnable {
    private int lastNum;

    /** Construct a task for printing 1, 2, ..., n */
    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        Thread thread4 = new Thread(new PrintChar('c', 100));
        thread4.start();

        try {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
                if (i == 50) {
                    thread4.join();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }

    }
}