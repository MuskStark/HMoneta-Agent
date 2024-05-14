package fan.summer.agent.service;

import fan.summer.agent.entity.SystemInfoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemInfoServiceTest {

    @Test
    void getSystemInfo() {
        SystemInfoService systemInfoService = new SystemInfoService();
        SystemInfoEntity systemInfo = systemInfoService.getSystemInfo();
        System.out.println(systemInfo);
    }
}