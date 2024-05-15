package fan.summer.agent.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import fan.summer.agent.entity.SystemInfoEntity;
import fan.summer.agent.service.HostServerInfoSendServer;
import fan.summer.agent.service.SystemInfoService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 类的详细说明
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/12
 */
@Component
public class SystemInfoReporter {

    private final static Logger LOG = LoggerFactory.getLogger(SystemInfoReporter.class);

    @Resource
    private SystemInfoService service;
    @Resource
    private HostServerInfoSendServer sendServer;

    @Scheduled(cron = "0/20 * * * * *")
    protected void infoCollection() throws JsonProcessingException {
        LOG.info("-------------开始上送系统信息定时任务-------------");
        SystemInfoEntity systemInfo = service.getSystemInfo();
        boolean b = sendServer.sendSysInfo(systemInfo);
        LOG.info("定时任务执行结果：{}", b);
        LOG.info("-------------结束上送系统信息定时任务-------------");
    }
}
