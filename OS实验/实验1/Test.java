public class Test {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2); // 创建缓冲池，设置容量为5
        Producer producer = new Producer(buffer); // 创建生产者
        Consumer consumer = new Consumer(buffer); // 创建消费者

        Thread producerThread = new Thread(producer); // 创建生产者线程
        Thread consumerThread = new Thread(consumer); // 创建消费者线程

        producerThread.start(); // 启动生产者线程
        consumerThread.start(); // 启动消费者线程
    }
}
