package fan.summer.agent.entity;

import lombok.Getter;
import lombok.Setter;
import oshi.hardware.NetworkIF;

import java.util.List;

/**
 * 系统详细信息类
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/12
 */
@Getter
@Setter
public class SystemInfoEntity {
    private Long agentId;
    private String cpuName;
    private double[] cupLoad;
    private long totalMemory;
    private long freeMemory;
    private long totalDisk;
    private String freeDisk;
    private List<NetworkIF> networkIp;
}
