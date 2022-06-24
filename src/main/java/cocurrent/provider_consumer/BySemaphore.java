package cocurrent.provider_consumer;

import java.util.concurrent.Semaphore;

/**
 * @author Hansel Ma
 * @since 2022/6/16
 */
public class BySemaphore {
    int count = 0;
    final Semaphore get = new Semaphore(0);
    final Semaphore put = new Semaphore(5);//初始令牌个数
    final Semaphore mutex = new Semaphore(1);


    public static void main(String[] args) {
        BySemaphore bySemaphore = new BySemaphore();
        new Thread(bySemaphore.new Producer(), "生产者-1").start();
        new Thread(bySemaphore.new Consumer(), "消费者-1").start();
        new Thread(bySemaphore.new Consumer(), "消费者-2").start();
        new Thread(bySemaphore.new Producer(), "生产者-2").start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    put.acquire();//注意顺序
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产: " + count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    get.release();
                }

            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    get.acquire();//注意顺序
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费: " + count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    put.release();
                }
            }
        }
    }
}