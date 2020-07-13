import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xwzhou
 * @version 1.0
 * @date 2020/6/11 14:03
 */
@Data
@Accessors(chain = true)
public class Entity implements Serializable, Comparable<Entity> {

    private static final long serialVersionUID = -2344438523186505173L;

    @Override
    public int compareTo(Entity o) {
        return 0;
    }

    private Integer sum;

    private String str;

}
