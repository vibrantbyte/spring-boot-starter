package io.github.vibrantbyte.storage.datasource.aop;

import io.github.vibrantbyte.storage.datasource.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author vibrant byte
 */
@Slf4j
@Aspect
@Order(2)
public class DataSourceSelectorAspect {

    /**
     * 占位符
     */
    private static final String SEAT = "#";
    /**
     * 正则匹配 字母，数字或者_
     */
    private static final String PATTERN = "\\w+";

    @Before("@annotation(selector)")
    public void changeDataSource(JoinPoint point,DataSourceSelector selector){
        log.debug("selector change data source :{} > {}", selector.name(), point.getSignature());
        if (selector.mandatoryMaster()){
            DynamicDataSourceHolder.setMasterRouteOnly(selector.mandatoryMaster());
            log.debug("data source  mandatory master :{} > {}", selector.name(), point.getSignature());
        }

        // 将参数值设置到注解属性上
        MethodSignature signature = (MethodSignature) point.getSignature();
        String sourceName;
        if(StringUtils.isNotEmpty(sourceName = selector.name()) && selector.name().indexOf(SEAT) > -1){
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(selector.name());
            while (matcher.find()) {
                sourceName = matcher.group();
                if (StringUtils.isNotEmpty(sourceName)){
                    break;
                }
            }
            log.info("动态数据源切换获取参中的数据库名称参数为：{}",sourceName);
            //获取参数名称集合
            String[] parameterNames = signature.getParameterNames();
            if (null != parameterNames && parameterNames.length > 0){
                for (int i = 0; i < parameterNames.length; i++) {
                    String parameterName = parameterNames[i];
                    if (sourceName.equals(parameterName)) {
                        //动态切换
                        DynamicDataSourceHolder.setCurrentGroupName(point.getArgs()[i].toString());
                        log.info("动态数据源切换获取参中的数据库名称为:{},切换成功。",point.getArgs()[i]);
                    }
                }
            }
        }else{
            DynamicDataSourceHolder.setCurrentGroupName(selector.name());
        }
    }

    @After("@annotation(selector)")
    public void restoreDataSource(JoinPoint point,DataSourceSelector selector) {
        log.debug("selector clear data source :{} > {}",selector.name(), point.getSignature());
        if (selector.mandatoryMaster()){
            DynamicDataSourceHolder.setMasterRouteOnly(!selector.mandatoryMaster());
            log.debug("data reduction default setting :{} > {}", selector.name(), point.getSignature());
        }
        DynamicDataSourceHolder.clear();
    }

}
