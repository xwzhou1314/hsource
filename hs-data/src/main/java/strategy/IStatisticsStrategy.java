package strategy;

/**
 * 任务统计策略模式
 *
 * @author: xwzhou
 * @date: Created in 2020/6/20 22:31
 */
public interface IStatisticsStrategy {

    /**
     * 处理任务列表     进行生成 Excel 表格并上传至阿里云
     *
     * @return 阿里云地址
     */
    String solveStatisticsList();
}
