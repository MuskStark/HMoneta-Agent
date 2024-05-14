package fan.summer.agent.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import fan.summer.agent.HMonetaAgentApplication;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller请求与响应的日志切面类
 *
 * @author phoebej
 * @version 1.00
 * @Date 2024/5/10
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerLogAspect.class);
    final ObjectMapper mapper = new ObjectMapper();
    @Pointcut("execution(public * fan.summer..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Signature signature = joinPoint.getSignature();
            String name = signature.getName();

            // 打印请求信息
            LOG.info("------------- 开始 -------------");
            LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
            LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
            LOG.info("远程地址: {}", request.getRemoteAddr());

            // 打印请求参数
            Object[] args = joinPoint.getArgs();
            // LOG.info("请求参数: {}", JSONObject.toJSONString(args));

            // 排除特殊类型的参数，如文件类型
            Object[] arguments = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ServletRequest
                        || args[i] instanceof ServletResponse
                        || args[i] instanceof MultipartFile) {
                    continue;
                }
                arguments[i] = args[i];
            }
            LOG.info("请求参数: {}", mapper.writeValueAsString(arguments));
        }catch (Exception e){
            LOG.error("ControllerLogAspect.doBefore() error", e);
        }
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        LOG.info("返回结果: {}", mapper.writeValueAsString(result));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }



}
