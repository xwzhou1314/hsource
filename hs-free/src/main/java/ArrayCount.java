import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/3 16:12
 */
public class ArrayCount extends ArrayTest {
    public int count = 0;

    @Override
    public void add(int element) {
        super.add(element);
        ++count;
    }

    @Override
    public void addAll(int[] elements) {
        super.addAll(elements);
        count += elements.length;
    }

    /**
     * 从基类的作者的角度来看，这个类实现的功能完全没有变化。而且所有自动化测试也都通过来了。
     * 但是基类的作者忘记了继承的类。而继承类的作者被错误吵醒了。
     * 现在ArrayCount的addAll()调用父类的addAll()，后者在内部调用add()，而add()被继承类重载了。
     * 因此，每次继承类的add()被调用时，count都会增加，然后在继承类的addAll()被调用时再次增加。
     * count被增加了两次。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个可重用固定个数的线程池
        ExecutorService threadPool=new ThreadPoolExecutor(2,5,
            1L,TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() ->
            {
                try {
                    // 打印正在执行的缓存线程信息
                    System.out.println(Thread.currentThread().getName()
                        + "正在被执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }



}
