//Buffer.java是缓冲池，含有一个用于存放产品的buffer队列
import java.util.LinkedList;//实现队列数据结构
import java.util.Queue;//用于定义队列的操作

public class Buffer {
    private final Queue<Integer> buffer;//私有的buffer队列
    private final int capacity;//缓冲区容量

    //Buffer类的构造函数
    public Buffer(int capacity) {
        this.capacity = capacity;//初始化缓冲区容量
        this.buffer = new LinkedList<>();//创建空队列buffer
    }

    // 生产产品
    public synchronized void produce(int item) throws InterruptedException {
        // 如果缓冲区已满，则等待
        while (buffer.size() == capacity ) {
            wait();
        }
        // 将产品放入缓冲区
        buffer.offer(item);
        // 输出提示信息
        System.out.println("生产者生产了产品: " + item + "，当前缓冲区大小: " + buffer.size());
        if(buffer.isEmpty()){
            System.out.println("缓冲区为空，若有消费者想继续消费，则需等待");
        } else if (buffer.size()==capacity) {
            System.out.println("缓冲区已满，若有生产者想继续生产，则需等待");
        }
        // 唤醒等待的消费者线程，以便等待的消费者进程可以重新竞争锁
        notifyAll();
    }

    // 消费产品
    public synchronized int consume() throws InterruptedException {
        // 如果缓冲区为空，则等待
        while (buffer.isEmpty()) {
            wait();
        }
        // 从缓冲区取出产品
        int item = buffer.poll();
        // 输出提示信息
        System.out.println("消费者消费了产品: " + item + "，当前缓冲区大小: " + buffer.size());
        if(buffer.isEmpty()){
            System.out.println("缓冲区为空，若有消费者想继续消费，则需等待");
        } else if (buffer.size()==capacity) {
            System.out.println("缓冲区已满，若有生产者想继续生产，则需等待");
        }
        // 唤醒等待的生产者线程
        notifyAll();
        return item;
    }
}
