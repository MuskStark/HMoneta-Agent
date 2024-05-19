package fan.summer.agent;

import fan.summer.agent.common.ProjectBanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HMonetaAgentApplication {

    private static final Logger LOG = LoggerFactory.getLogger(HMonetaAgentApplication.class);

    public static void main(String[] args) {
        MDC.put("LOG_ID", String.valueOf(System.currentTimeMillis()));
        SpringApplication application = new SpringApplication(HMonetaAgentApplication.class);
        application.setBanner(new ProjectBanner());
        Environment env = application.run(args).getEnvironment();
        LOG.info("服务启动成功!");
        LOG.info("启动成功，项目地址：http://localhost:{}", env.getProperty("server.port"));
    }

}
