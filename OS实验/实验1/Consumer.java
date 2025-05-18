import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;
    private Random random;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // 设置消费次数为10次
                int item = buffer.consume(); // 从缓冲区消费产品
                Thread.sleep(random.nextInt(1000)); // 模拟消费时间
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
