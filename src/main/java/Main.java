import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        int[] revenue = new int[] {100, 1000, 250, 300, 900};
        int[] revenue2 = new int[] {450, 600};
        int[] revenue3 = new int[] {300, 300, 400, 100, 50};

        Runnable task1 = () -> addSum(revenue);
        Runnable task2 = () -> addSum(revenue2);
        Runnable task3 = () -> addSum(revenue3);

        Thread thread1 = new Thread(null, task1, "Первый");
        Thread thread2 = new Thread(null, task2, "Второй");
        Thread thread3 = new Thread(null, task3, "Третий");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Общая выручка " + sum);

    }

    public static void addSum(int[] revenue) {

        int currentAmount = 0;
        for (int i = 0; i < revenue.length; i++) {
            currentAmount += revenue[i];
        }

        sum.getAndAdd(currentAmount);
    }
}
