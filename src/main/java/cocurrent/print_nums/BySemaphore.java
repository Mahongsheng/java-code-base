package cocurrent.print_nums;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hansel Ma
 * @since 2022/6/24
 */
public class BySemaphore {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count = 0;

    public void printNum() {
        int limit = 30;
        lock.lock();
        try {
            while (count < limit) {
                System.out.println("线程" + Thread.currentThread().getName() + "打印数字：" + ++count);
                condition.signal();
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BySemaphore bySemaphore = new BySemaphore();

        Thread thread1 = new Thread(bySemaphore::printNum, "-1");
        Thread thread2 = new Thread(bySemaphore::printNum, "-2");
        thread1.start();
        thread2.start();
    }
}
