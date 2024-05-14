package fan.summer.agent.service;

import fan.summer.agent.entity.SystemInfoEntity;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;

import java.util.List;

/**
 * 用以收集系统信息
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/12
 */
@Service
public class SystemInfoService {

    public SystemInfoEntity getSystemInfo(){
        SystemInfoEntity info = new SystemInfoEntity();
        // 初始化系统信息对象
        SystemInfo si = new SystemInfo();
        // 获取硬件抽象层对象
        HardwareAbstractionLayer hal = si.getHardware();
        // 获取操作系统对象
        OperatingSystem os = si.getOperatingSystem();
        // 获取计算机系统对象
        ComputerSystem computerSystem = hal.getComputerSystem();
        // 获取计算机制造商
        computerSystem.getManufacturer();
        // TODO：获取CPU使用信息
        info.setCupLoad(hal.getProcessor().getProcessorCpuLoad(500L));
        // TODO：获取内存使用信息
        info.setTotalMemory(hal.getMemory().getTotal());
        info.setFreeMemory(hal.getMemory().getAvailable());
        // TODO：获取磁盘使用信息
        info.setTotalDisk(hal.getDiskStores().getFirst().getSize());
        // TODO：获取网络使用信息
        List<NetworkIF> networkIFs = hal.getNetworkIFs();
        info.setNetworkIp(networkIFs);
        return info;
    }
}
