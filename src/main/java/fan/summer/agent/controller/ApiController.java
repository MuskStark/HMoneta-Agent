package fan.summer.agent.controller;

import fan.summer.agent.common.ApiRestResponse;
import fan.summer.agent.entity.ConfigEntity;
import fan.summer.agent.service.HostServerInfoSendServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 实现Agent通信接口
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/10
 */
@RestController
@RequestMapping("/agent/api")
public class ApiController {

    @Resource
    private HostServerInfoSendServer sendServer;

    @GetMapping("/status")
    public ApiRestResponse<Integer> agentStatus() {
        return ApiRestResponse.success();
    }
    @PostMapping("/config")
    public ApiRestResponse<String> agentConfig(@RequestBody ConfigEntity config) {
        assert config != null;
        sendServer.setConfig(config);
        return ApiRestResponse.success();
    }
}
