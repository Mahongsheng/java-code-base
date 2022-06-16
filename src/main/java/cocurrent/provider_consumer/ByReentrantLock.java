package cocurrent.provider_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hansel Ma
 * @since 2022/6/16
 */
public class ByReentrantLock {
    private int count = 0;
    private final int maxNum = 3;

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(10, 10, 100L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024), new ThreadPoolExecutor.AbortPolicy());
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public static void main(String[] args) {
        ByReentrantLock byReentrantLock = new ByReentrantLock();

        for (int i = 0; i < 2; i++) {
            executorService.submit(byReentrantLock.new Producer());
        }

        for (int i = 0; i < 2; i++) {
            executorService.submit(byReentrantLock.new Consumer());
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取锁
                lock.lock();
                try {
                    while (count == maxNum) {
                        System.out.println(Thread.currentThread().getName() + "生产能力达到上限，进入等待状态");
                        producerCondition.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                    //唤醒消费者
                    consumerCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //获取锁
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == 0) {
                        System.out.println(Thread.currentThread().getName() + "没有资源可消费，进入等待状态");
                        consumerCondition.await();
                    }

                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                    // 唤醒生产者
                    producerCondition.signal();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
