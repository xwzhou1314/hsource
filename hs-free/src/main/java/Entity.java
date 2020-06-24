import lombok.Data;

import java.io.Serializable;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/11 14:03
 */
@Data
public class Entity implements Serializable, Comparable<Entity> {

    private static final long serialVersionUID = -2344438523186505173L;

    public int compareTo(Entity o) {
        return 0;
    }

}
