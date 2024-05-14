package fan.summer.agent.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 接收Master服务器下发的配置信息
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/12
 */
@Getter
@Setter
public class ConfigEntity {
    private String reportUrl;
    private Long agentId;
}
