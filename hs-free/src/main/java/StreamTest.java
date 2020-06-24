import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/11 14:27
 */
public class StreamTest implements Serializable {

    private static final long serialVersionUID = -507571268547563015L;

    public static void main(String[] args) {

        // 测试 filter
        // testStreamFilter();

        // 测试 map
        // testStreamMap();

        // 测试 sorted
        // testStreamsorted();

        // 测试 distinct
        // testDistinct();

        // filter、map、sort、limit
        testfilterMapSortLimit();
    }

    private static void testfilterMapSortLimit() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        long count = strings.stream().filter(string -> string.length() >= 6).map(String::length)
            // 6
            // 12
            // 6
            // 10
            // 6
            .sorted()
            // 6
            // 6
            // 6
            // 10
            // 12
            .limit(3).distinct().count();
        System.out.println(count);
        // 6 12 10
    }

    private static void testDistinct() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().sorted(Comparator.reverseOrder());

    }

    private static void testStreamsorted() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        numbers.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
    }

    private static void testStreamMap() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().limit(4).map( i -> i*i).forEach(System.out::println);
    }

    private static void testStreamFilter() {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        Stream<String> stream = strings.stream();
        stream.filter(v -> !v.isEmpty()).forEach(System.out::println);
    }

}
