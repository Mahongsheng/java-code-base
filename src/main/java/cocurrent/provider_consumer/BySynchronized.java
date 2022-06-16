package cocurrent.provider_consumer;

/**
 * @author Hansel Ma
 * @since 2022/6/16
 */
public class BySynchronized {
    private int num = 0;

    public synchronized void provide() throws InterruptedException {
        while (num != 0) {// 不用if防止虚假唤醒，官方API文档建议用while，if用了会出错！
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "卖出：" + num + "个苹果");
        this.notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (num == 0) {// 不用if防止虚假唤醒，官方API文档建议用while，if用了会出错！
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "买了：" + num + "个苹果");
        this.notify();
    }

    //这一种只需要在生产和消费方法加上synchronized锁就好了，然后生产完即通知消费即可，如果num为0则消费停止然后生产，num=1则反之
    public static void main(String[] args) {
        BySynchronized bySynchronized = new BySynchronized();
        // 线程A（生产者）获取锁后，调用notify方法通知锁对象的等待队列，使得线程B从等待队列进入阻塞队列
        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                try {
                    bySynchronized.provide();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "水果店").start();

        // 当线程B（消费者）调用wait()方法后，线程B让出锁，自己进入等待状态，同时加入锁对象的等待队列
        new Thread(() -> {
            for (int j = 0; j < 5; j++)
                try {
                    bySynchronized.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "顾客").start();
    }
}
