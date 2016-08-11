package ua.goit.java.appForRestaurant.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Aspect
@EnableAspectJAutoProxy
public class AopConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(public * *(..))")
    private void allPublicMethods(){
    }

    @Around("allPublicMethods()")
    public Object allOperations(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object result = null;
        try {
            LOGGER.info("Logger before: " + methodName);
            result = point.proceed();
            LOGGER.info("Logger after: " + methodName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
