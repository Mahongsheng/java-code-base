package cocurrent.provider_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Hansel Ma
 * @since 2022/6/16
 */
public class ByQueue {

    private final BlockingQueue<Toy> blockingDeque = new LinkedBlockingDeque<>(10);

    public static void main(String[] args) {
        ByQueue ByQueue = new ByQueue();

        new Thread(ByQueue.new Producer()).start();
        new Thread(ByQueue.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toy toy = new Toy(i + "");
                try {
                    blockingDeque.put(toy);
                    System.out.println("生产者" + Thread.currentThread().getName() + " 生产玩具" + toy.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Toy toy = blockingDeque.take();
                    System.out.println("消费者" + Thread.currentThread().getName() + " 消费玩具" + toy.getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Toy {
        private String name;

        public Toy(String name) {
            this.name = name;
        }

        public String getName() {
            return "toy " + name;
        }
    }
}
