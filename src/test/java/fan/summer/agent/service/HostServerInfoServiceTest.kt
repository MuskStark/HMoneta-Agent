package fan.summer.agent.service

import fan.summer.agent.entity.SystemInfoEntity
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HostServerInfoServiceTest {

    @Test
    fun sentSysInfo() {
        val test = HostServerInfoService()
        val a = SystemInfoEntity()
        print( test.sentSysInfo(a))
    }
}