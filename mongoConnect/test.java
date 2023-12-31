

public class test implements Runnable{
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getId() + " Value " + i);
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new test());
        t1.start(); // Starts the first thread

        Thread t2 = new Thread(new test());
        t2.start(); // Starts the second thread
    }
}
