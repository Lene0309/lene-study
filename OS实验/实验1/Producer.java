import java.util.Random;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final Random random;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // 设置生产次数为10次
                int item = random.nextInt(100); // 随机生成产品
                buffer.produce(item); // 将产品放入缓冲区
                Thread.sleep(random.nextInt(1000)); // 模拟生产时间
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
