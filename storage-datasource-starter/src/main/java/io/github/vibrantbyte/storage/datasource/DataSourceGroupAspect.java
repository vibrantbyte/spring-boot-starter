package io.github.vibrantbyte.storage.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author xiaoyueya
 */
@Slf4j
@Aspect
@Order(1)
public class DataSourceGroupAspect {

    @Pointcut("@annotation(io.github.vibrantbyte.storage.datasource.DataSourceGroup) || @within(io.github.vibrantbyte.storage.datasource.DataSourceGroup)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        // 代理对象
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        //获取代理方法上的注解
        DataSourceGroup anno = method.getAnnotation(DataSourceGroup.class);
        if (anno == null) {
            //获取代理类上的注解
            anno = (DataSourceGroup) pjp.getSignature().getDeclaringType().getAnnotation(DataSourceGroup.class);
        }
        if (anno != null) {
            try {
                DynamicDataSourceHolder.setCurrentGroupName(anno.name());
                return pjp.proceed();
            } finally {
                DynamicDataSourceHolder.clear();
            }
        } else {
            return pjp.proceed();
        }
    }
}
