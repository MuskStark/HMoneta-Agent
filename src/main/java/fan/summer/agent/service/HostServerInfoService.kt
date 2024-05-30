package fan.summer.agent.service

import fan.summer.agent.entity.ConfigEntity
import fan.summer.agent.entity.SystemInfoEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class HostServerInfoService {

    private val log: Logger = LoggerFactory.getLogger(HostServerInfoService::class.java)

    var config:ConfigEntity = ConfigEntity()


    private var canReport = false

    fun sentSysInfo(info: SystemInfoEntity): Boolean {
        log.info("开始报送系统信息")
        log.info("是否允许上报：{}", this.canReport)
        return true
    }
}