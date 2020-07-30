import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 亲属关系以及code枚举
 *
 * @author xwzhou
 * @date 2020/7/28 11:06
 */
@Getter
public enum RelationEnum {

    Relation_ZREO_ONE(4,"01", "本人"),
    Relation_ZREO_TWO(4,"02", "户主"),
    Relation_ZREO_THREE(4,"03", "小集体户户主"),
    Relation_ONE_ZERO(4,"10", "配偶"),
    Relation_ONE_ONE(4,"11", "夫"),
    Relation_ONE_TWO(4,"12", "妻"),

    Relation_TWO_ZERO(3,"20", "子"),
    Relation_TWO_ONE(3,"21", "独生子"),
    Relation_TWO_TWO(3,"22", "长子"),
    Relation_TWO_THERR(3,"23", "次子"),
    Relation_TWO_FOUR(3,"24", "三子"),
    Relation_TWO_FIVE(3,"25", "四子"),
    Relation_TWO_SIX(3,"26", "五子"),
    RELATION_TWO_SEVEN(3,"27", "养子或继子"),
    RELATION_TWO_SEVEN_ONE(3,"271", "养子"),
    RELATION_TWO_SEVEN_TWO(3,"272", "继子"),
    RELATION_TWO_EIGHT(3,"28", "女婿"),
    RELATION_TWO_NINE(3,"29", "其他子"),

    RELATION_THREE_ZERO(3,"30", "女"),
    RELATION_THREE_ONE(3,"31", "独生女"),
    RELATION_THREE_TWO(3,"32", "长女"),
    RELATION_THREE_THREE(3,"33", "二女"),
    RELATION_THREE_FOUR(3,"34", "三女"),
    RELATION_THREE_FIVE(3,"35", "四女"),
    RELATION_THREE_SIX(3,"36", "五女"),
    RELATION_THREE_SEVEN(3,"37", "养女或者继女"),
    RELATION_THREE_SEVEN_ONE(3,"371", "养女"),
    RELATION_THREE_SEVEN_TWO(3,"372", "继女"),
    RELATION_THREE_EIGHT(3,"38", "儿媳"),
    RELATION_THREE_NINE(3,"39", "其他女儿"),


    RELATION_FOUR_ZERO(2,"40", "孙子、孙女或外孙子、外孙女"),
    RELATION_FOUR_ONE(2,"41", "孙子"),
    RELATION_FOUR_TWO(2,"42", "孙女"),
    RELATION_FOUR_THREE(2,"43", "外孙子"),
    RELATION_FOUR_FOUR(2,"44", "外孙女"),
    RELATION_FOUR_FIVE(2,"45", "孙媳妇或外孙媳妇"),
    RELATION_FOUR_FIVE_ONE(2,"451", "孙女婿"),
    RELATION_FOUR_FIVE_TWO(2,"452", "外孙女婿"),
    RELATION_FOUR_SIX(2,"46", "孙女婿或外孙女婿"),
    RELATION_FOUR_SIX_ONE(2,"461", "孙女婿"),
    RELATION_FOUR_SIX_TWO(2,"462", "外孙女婿"),
    RELATION_FOUR_SEVEN(1,"47", "曾孙子或曾外孙子"),
    RELATION_FOUR_SEVEN_ONE(1,"471", "曾孙子"),
    RELATION_FOUR_SEVEN_TWO(1,"472", "曾外孙子"),
    RELATION_FOUR_EIGHT(1,"48", "曾孙女或曾外孙女"),
    RELATION_FOUR_EIGHT_ONE(1,"481", "曾孙女"),
    RELATION_FOUR_EIGHT_ONE_TWO(1,"482", "曾外孙女"),
    RELATION_FOUR_NINE(1,"49", "其他孙子、孙女或外孙子、外孙女"),
    RELATION_FOUR_NINE_ONE(1,"491", "其他孙子"),
    RELATION_FOUR_NINE_TWO(1,"492", "其他孙女"),
    RELATION_FOUR_NINE_THREE(1,"493", "其他外孙子"),
    RELATION_FOUR_NINE_FOUR(1,"494", "其他外孙女"),


    RELATION_FIVE_ZERO(5,"50", "父母"),
    RELATION_FIVE_ONE(5,"51", "父亲"),
    RELATION_FIVE_TWO(5,"52", "母亲"),
    RELATION_FIVE_THREE(5,"53", "公公"),
    RELATION_FIVE_FOUR(5,"54", "婆婆"),
    RELATION_FIVE_FIVE(5,"55", "岳父"),
    RELATION_FIVE_SIX(5,"56", "岳母"),
    RELATION_FIVE_SEVEN(5,"57", "继父或者养父"),
    RELATION_FIVE_SEVEN_ONE(5,"571", "继父"),
    RELATION_FIVE_SEVEN_TWO(5,"572", "养父"),
    RELATION_FIVE_EIGHT(5,"58", "继母或养母"),
    RELATION_FIVE_EIGHT_ONE(5,"581", "继母"),
    RELATION_FIVE_EIGHT_TWO(5,"582", "养母"),
    RELATION_FIVE_NINE(5,"59", "其他父母关系"),
    RELATION_FIVE_NINE_ONE(5,"591", "其他父关系"),
    RELATION_FIVE_NINE_TWO(5,"592", "其他母关系"),


    RELATION_SIX_ZERO(6,"60", "祖父母或者外祖父母"),
    RELATION_SIX_ONE(6,"61", "祖父"),
    RELATION_SIX_TWO(6,"62", "祖母"),
    RELATION_SIX_THREE(6,"63", "外祖父"),
    RELATION_SIX_FOUR(6,"64", "外祖母"),
    RELATION_SIX_FIVE(6,"65", "配偶的祖父母或外祖父母"),
    RELATION_SIX_FIVE_ONE(6,"651", "配偶的祖父"),
    RELATION_SIX_FIVE_TWO(6,"652", "配偶的祖母"),
    RELATION_SIX_FIVE_THREE(6,"653", "配偶的外祖父"),
    RELATION_SIX_FIVE_FOUR(6,"654", "配偶的外祖母"),
    RELATION_SIX_SIX(6,"66", "曾祖父"),
    RELATION_SIX_SEVEN(6,"67", "曾祖母"),
    RELATION_SIX_EIGHT(6,"68", "配偶的曾祖父母"),
    RELATION_SIX_EIGHT_ONE(6,"681", "配偶的曾祖父"),
    RELATION_SIX_EIGHT_TWO(6,"682", "配偶的曾祖母"),
    RELATION_SIX_NINE(6,"69", "其他祖父母或外祖父母关系"),
    RELATION_SIX_NINE_ONE(6,"691", "其他祖父关系"),
    RELATION_SIX_NINE_TWO(6,"692", "其他祖母关系"),
    RELATION_SIX_NINE_THREE(6,"693", "其他外祖父关系"),
    RELATION_SIX_NINE_FOUR(6,"694", "其他外祖母关系"),


    RELATION_SEVEN_ZERO(4,"70", "兄弟姐妹"),
    RELATION_SEVEN_ONE(4,"71", "兄"),
    RELATION_SEVEN_TWO(4,"72", "嫂"),
    RELATION_SEVEN_THREE(4,"73", "弟"),
    RELATION_SEVEN_FOUR(4,"74", "弟媳"),
    RELATION_SEVEN_FIVE(4,"75", "姐姐"),
    RELATION_SEVEN_SIX(4,"76", "姐夫"),
    RELATION_SEVEN_SEVEN(4,"77", "妹妹"),
    RELATION_SEVEN_EIGHT(4,"78", "妹夫"),
    RELATION_SEVEN_NINE(4,"79", "其他兄弟姐妹"),
    RELATION_SEVEN_NINE_ONE(4,"791", "其他兄"),
    RELATION_SEVEN_NINE_TWO(4,"792", "其他弟"),
    RELATION_SEVEN_NINE_THREE(4,"793", "其他姐"),
    RELATION_SEVEN_NINE_FOUR(4,"794", "其他妹"),


    RELATION_EIGHT_ZERO(5,"81", "伯父"),
    RELATION_EIGHT_TWO(5,"82", "伯母"),
    RELATION_EIGHT_THREE(5,"83", "叔父"),
    RELATION_EIGHT_FOUR(5,"84", "婶母"),
    RELATION_EIGHT_FIVE(5,"85", "舅父"),
    RELATION_EIGHT_SEX(5,"86", "舅母"),
    RELATION_EIGHT_SEVEN(5,"87", "姨父"),
    RELATION_EIGHT_EIGHT(5,"88", "姨母"),
    RELATION_EIGHT_NINE(5,"89", "姑父"),

    RELATION_NINE_ZERO(5,"90", "姑母"),
    RELATION_NINE_ONE(4,"91", "堂兄弟、堂姐妹"),
    RELATION_NINE_ONE_ONE(4,"911", "堂兄"),
    RELATION_NINE_ONE_TWO(4,"912", "堂弟"),
    RELATION_NINE_ONE_THREE(4,"913", "堂姐"),
    RELATION_NINE_ONE_FOUR(4,"914", "堂妹"),
    RELATION_NINE_TWO(4,"92", "表兄弟、表姐妹"),
    RELATION_NINE_TWO_ONE(4,"921", "表兄"),
    RELATION_NINE_TWO_TWO(4,"922", "表弟"),
    RELATION_NINE_TWO_THREE(4,"923", "表姐"),
    RELATION_NINE_TWO_FOUR(4,"924", "表妹"),
    RELATION_NINE_THREE(3,"93", "侄子"),
    RELATION_NINE_FOUR(3,"94", "侄女"),
    RELATION_NINE_FIVE(3,"95", "外甥"),
    RELATION_NINE_SIX(3,"96", "外甥女"),
    RELATION_NINE_SEVEN(0,"97", "其他亲属"),
    RELATION_NINE_EIGHT(0,"98", "保姆"),
    RELATION_NINE_NINE(0,"99", "非亲属");


    private Integer level;
    private String relationCode;
    private String relation;

    RelationEnum(Integer level, String relationCode, String relation) {
        this.level = level;
        this.relationCode = relationCode;
        this.relation = relation;
    }

    public static Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(129);
        for (RelationEnum airlineTypeEnum : RelationEnum.values()) {
            map.put(airlineTypeEnum.getRelationCode(), airlineTypeEnum.getRelation());
        }
        return map;
    }
}
