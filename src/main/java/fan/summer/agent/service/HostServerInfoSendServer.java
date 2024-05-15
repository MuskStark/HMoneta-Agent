package fan.summer.agent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fan.summer.agent.entity.ConfigEntity;
import fan.summer.agent.entity.SystemInfoEntity;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 实现Agent通信接口
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/10
 */
@Service
public class HostServerInfoSendServer {

    private final static Logger LOG = LoggerFactory.getLogger(HostServerInfoSendServer.class);
    private ConfigEntity config;
    @Resource
    private RestTemplate restTemplate;

    private boolean canReport = false;

    public void setConfig(ConfigEntity config){
        assert config != null;
        this.config = config;
        this.canReport = true;
    }

    /**
     * 发送系统信息至指定URL。
     *
     * @param info 包含系统信息的实体对象，将被转换成JSON格式发送。
     * @return boolean 如果成功发送系统信息则返回true，如果不能发送（如报告功能被禁用）则返回false。
     * @throws JsonProcessingException 当转换系统信息实体为JSON字符串时发生错误。
     */
    public boolean sendSysInfo(SystemInfoEntity info) throws JsonProcessingException {
        LOG.info("开始报送系统信息");
        LOG.info("是否允许上报：{}", this.canReport);
        if(this.canReport) {
            // 请求头，设置Content-Type为application/json
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(info);
            LOG.info("待报送信息:{}", json);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            String reportUrl = config.getReportUrl()+"/agent/api/report";
            LOG.info("报送地址:{}", reportUrl);
            // 发送POST请求并获取响应
            ResponseEntity<String> response = restTemplate.exchange(reportUrl, HttpMethod.POST, entity, String.class);
            LOG.info("报送结果:{}", response.getStatusCode());
            return true;
        }else {
            LOG.error("报送失败");
            return false;
        }
    }


}
