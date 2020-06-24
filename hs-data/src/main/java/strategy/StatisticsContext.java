package strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 统计任务的工厂
 *
 * @author: xwzhou
 * @date: Created in 2020/6/20 22:40
 */
@Service
public class StatisticsContext {


    @Autowired
    private final Map<String, IStatisticsStrategy> strategyMap = new ConcurrentHashMap<>();

    public StatisticsContext(Map<String, IStatisticsStrategy> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    /**
     * 策略模式
     *
     * @param poolId          策略
     * @return
     */
    public String solveStatisticsList(String poolId ) {
        return strategyMap.get(poolId).solveStatisticsList();
    }
}
