

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author hux
 * @Date 2019/5/22、15:41
 * @Version 1.0
 **/

public class ThreadTest {

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        List<Future> futureList = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            Callable<Void> task = () -> {
                return null;
            };
            Future submit = CommonThreadPool.submit(task);
            futureList.add(submit);
        }
        //主线程处理其他工作,让子线程异步去执行.
        System.out.println("now waiting sub thread done.");
        //主线程其他工作完毕,等待子线程的结束, 调用future.get()系列的方法即可。
        List<Void> articleList = new ArrayList<>();
        try {
            for (Future future : futureList) {
                List<Void> list = (List<Void>) future.get();
                articleList.addAll(list);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - l1);
        System.out.println(articleList.size() + "   "+articleList);
        CommonThreadPool.shutdown();

    }



}