package com.hsource.item.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author xwzhou
 * @date 2020-03-20 10:55
 */
@Slf4j
@Component
@EnableScheduling
@JobHandler(value = "TestTask")
public class TestTask extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("TestTask");
        return SUCCESS;
    }
}
