import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/17 17:38
 */
public class HashTest implements Serializable {

    private static final long serialVersionUID = -6856770214598017428L;

    public static void main(String[] args) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L,1);
    }
}
