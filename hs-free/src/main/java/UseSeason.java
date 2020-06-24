/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/3 9:40
 */
public class UseSeason {
    /**
     * 将英文的季节转换成中文季节
     *
     * @param season
     * @return
     */
    public String getChineseSeason(Season season) {
        StringBuffer result = new StringBuffer();
        switch (season) {
            case SPRING:
                result.append("[中文：春天，枚举常量:").append(season.name()).append("，数据:").append(season.getCode()).append("]");
                break;
            case AUTUMN:
                result.append("[中文：秋天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            case SUMMER:
                result.append("[中文：夏天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            case WINTER:
                result.append("[中文：冬天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            default:
                result.append("地球没有的季节 " + season.name());
                break;
        }
        return result.toString();
    }

    public void doSomething() {
        for (Season s : Season.values()) {
            //这是正常的场景
            System.out.println(getChineseSeason(s));
        }
        // System.out.println(getChineseSeason(5));
        //此处已经是编译不通过了，这就保证了类型安全
    }

    public static void main(String[] arg) {
        UseSeason useSeason = new UseSeason();
        useSeason.doSomething();
    }
}
