package io.github.vibrantbyte.storage.datasource.aop;

import io.github.vibrantbyte.storage.datasource.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * @author vibrant byte
 */
@Slf4j
@Aspect
@Order(2)
public class DataSourceSelectorAspect {

    @Before("@annotation(selector)")
    public void changeDataSource(JoinPoint point,DataSourceSelector selector){
        log.debug("selector change data source :{} > {}", selector.name(), point.getSignature());
        if (selector.mandatoryMaster()){
            DynamicDataSourceHolder.setMasterRouteOnly(selector.mandatoryMaster());
            log.debug("data source  mandatory master :{} > {}", selector.name(), point.getSignature());
        }
        DynamicDataSourceHolder.setCurrentGroupName(selector.name());
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
