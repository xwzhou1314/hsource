import java.io.Serializable;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/1 15:01
 */
public class Singleton implements Serializable {

    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        synchronized (Singleton.class) {
            if (null == singleton) {
                singleton = new Singleton();
            }
            return singleton;
        }
    }

    public Object readResolve() {
        return singleton;
    }

}
