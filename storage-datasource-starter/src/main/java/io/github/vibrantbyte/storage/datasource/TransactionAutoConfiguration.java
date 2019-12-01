package io.github.vibrantbyte.storage.datasource;

import com.renrenche.spring.boot.patch.annotation.ConditionalOnFile;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author vibrant byte
 */
@Configuration
@ConditionalOnFile(resources = {"file:${spring.config.location}/mysql/mysql-${spring.profiles.active}.yml", "classpath:mysql/mysql-${spring.profiles.active}.yml"})
@ConditionalOnClass({DataSource.class})
@EnableTransactionManagement
public class TransactionAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @Autowired
    private Map<String, List<MasterSlaveDataSource>> allDataSources;

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() throws Throwable {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        return context.containsBean("txManager_default") ? (PlatformTransactionManager) context.getBean("txManager_default") : null;
    }

    @PostConstruct
    public void generateTransactionManagers() {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        allDataSources.forEach((groupName, dataSource) -> {
            String name = "txManager_" + groupName;

            AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(DynamicDataSourceTransactionManager.class);
            ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
            abd.setScope(scopeMetadata.getScopeName());
            String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, beanFactory));
            AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
            BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
            BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, beanFactory);

            DynamicDataSourceTransactionManager dsm = (DynamicDataSourceTransactionManager) context.getBean(name);
            dsm.setGroupName(groupName);
            dataSourceConfig.getGroups().forEach(dsConfig -> {
                if (groupName.equals(dsConfig.getName())) {
                    if(null == dsConfig.getMaster()){
                        dsm.setDefaultTimeout(dsConfig.getMasterSlaves().get(0).getMaster().getTransactionTimeout() == 0 ? -1 : dsConfig.getMasterSlaves().get(0).getMaster().getTransactionTimeout());
                    }else{
                        dsm.setDefaultTimeout(dsConfig.getMaster().getTransactionTimeout() == 0 ? -1 : dsConfig.getMaster().getTransactionTimeout());
                    }
                }
            });
        });
    }

}
