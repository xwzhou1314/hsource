import java.io.Serializable;

/**
 * @author: xwzhou
 * @date: Created in 2020/7/8 13:51
 */
public class EqualTest implements Serializable {
    private static final long serialVersionUID = 5505348750627294419L;

    public static void main(String[] args) {

    }


    /**
     * 这么做是为了防止计时攻击
     *
     * @param a
     * @param b
     * @return
     */
    boolean safeEqual(String a, String b) {

        // 首先 长度不等结果肯定不等
        if (a.length() != b.length()) {
            return false;
        }
        int equal = 0;


        // 1)
        for (int i = 0; i < a.length(); i++) {
            // 通过异或操作1^1=0, 1^0=1, 0^0=0，来比较每一位，
            // 如果每一位都相等的话，两个字符串肯定相等，最后存储累计异或值的变量equal必定为 0，否则为 1。
            equal |= a.charAt(i) ^ b.charAt(i);
        }

        return equal == 0;

        // 2) 不采用方法二防止计时攻击，可以通过计算时间不同，从而验证密码正确与否
//        for (int i = 0; i < a.length(); i++) {
//            equal |= a.charAt(i) ^ b.charAt(i);
//            if (equal != 0){
//                return Boolean.FALSE;
//            }
//        }


    }
}
