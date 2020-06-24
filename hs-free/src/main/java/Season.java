/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/3 9:38
 */
public enum Season {
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

    private int code;
    private Season(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
