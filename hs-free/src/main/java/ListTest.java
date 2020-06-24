import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/11 14:03
 */
public class ListTest implements Serializable {

    private static final long serialVersionUID = 5409587538535061511L;

    public static void main(String[] args) {

        Set<Entity> hashSet = new HashSet<Entity>();
        Entity entityOne = new Entity();
        hashSet.add(entityOne);


        Set<Entity> treeSet = new TreeSet<Entity>();
        Entity entityTwo = new Entity();
        treeSet.add(entityTwo);


    }

}
