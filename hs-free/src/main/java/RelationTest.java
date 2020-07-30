import java.util.*;

/**
 * 通过与户主的关系判断他的监护人，未成年人
 * 首先是父母，
 * 其次是爷爷奶奶外公外婆，
 * 再就是成年的兄弟姐妹
 * 其他亲属
 * @author: xwzhou
 * @date: Created in 2020/7/30 20:05
 */
public class RelationTest {
    public static void main(String[] args) {

        // 等级集合
        Map<Integer, List<Object>> personals = new HashMap<>();

        List<String> oneRelations =  Arrays.asList("子","女","孙","弟","妹");

        // 标记是否找到
        boolean isFind = false;
        // 如果本人关系不包含这些
        String name = "孙总";
        for (String s:oneRelations
             ) {
            if(name.contains(s)){
                isFind = true;
                break;
            }
        }
        if(! isFind){
            // 肯定是其他亲属，下面的步骤不用走了

        }

        // 1. 判断与户主等级差（未成年人肯定比户主低） 户主等级-本人等级
        int level = 1;
        // 大多数走这一步
        if(1 == level){
            for (Object object:personals.get(1)
                 ) {
                // 含有外字
                // 获取户主是男 为外公，女为外婆
                // 其妻为外婆， 夫为外公，配偶为男为父 女为外婆

                // 不含外字，与上相同

            }

        }

        if(2 == level){
            // 如果有“外”字，执行户主判断
            // 获取户主是男 为外公，女为母
            // 其妻为母， 夫为父，配偶为男为父 女为母
        }

        if(0 == level){
            // 户主肯定是她哥或者姐
            // 判断户主性别


        }

    }
}
