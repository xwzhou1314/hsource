import java.util.ArrayList;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/3 16:11
 */
public class ArrayTest {
    private ArrayList<Integer> a = new ArrayList<Integer>();

    public void add(int element) {
        a.add(element);
    }

    public void addAll(int[] elements) {
        for (int i = 0; i < elements.length; ++i) {
            // this line is going to be changed
            // begin  a.add(elements[i]);
            // end add(elements[i]);
            add(elements[i]);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            CommonThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(this.hashCode() / 1000);
                        for (int j = 0; j < 10; j++) {
                            System.out.println(this.hashCode() + ":" + j);
                            Thread.sleep(this.hashCode() % 2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("thread %d finished", this.hashCode()));
                }
            });
        }
        CommonThreadPool.shutdown();
    }


}
