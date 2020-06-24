import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CommonThreadPool
 * @Description TODO
 * @Author hux
 * @Date 2019/5/22、15:40
 * @Version 1.0
 **/

public class CommonThreadPool {

    /**
     * 线程池
     */
    public static  ExecutorService exec = new ThreadPoolExecutor(4, 8, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void execute(Runnable command) {
        exec.execute(command);
    }

    /**
     * 子线程执行结束future.get()返回null，若没有执行完毕，主线程将会阻塞等待
     * @param command
     * @return
     */
    public static Future submit(Runnable command) {
        return exec.submit(command);
    }

    /**
     * 子线程中的返回值可以从返回的future中获取：future.get();
     * @param command
     * @return
     */
    public static Future submit(Callable command) {
        return exec.submit(command);
    }

    public static void shutdown(){
        exec.shutdown();
    }

}