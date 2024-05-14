package fan.summer.agent.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import fan.summer.agent.entity.SystemInfoEntity;
import fan.summer.agent.service.HostServerInfoSendServer;
import fan.summer.agent.service.SystemInfoService;
import jakarta.annotation.Resource;
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
public class SystemInfoCollectors {

    @Resource
    private SystemInfoService service;
    @Resource
    private HostServerInfoSendServer sendServer;

    @Scheduled(cron = "0/5 * * * * *")
    protected void infoCollection() throws JsonProcessingException {
        SystemInfoEntity systemInfo = service.getSystemInfo();
        boolean b = sendServer.sendSysInfo(systemInfo);
    }
}
