package io.github.vibrantbyte.config.hotload.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author vibrant byte
 *
 */
@Slf4j(topic = "hot-load-starter")
@Configuration
@ConditionalOnClass(ContextRefresher.class)
@EnableConfigurationProperties(ServiceConfigInfo.class)
@ConditionalOnProperty("spring.config.location")
@AutoConfigureAfter(RefreshResolver.class)
@Import(RefreshResolver.class)
public class HotLoadAutoConfiguration {

    private final ContextRefresher contextRefresher;
    private final ServiceConfigInfo serviceConfigInfo;

    public HotLoadAutoConfiguration(ContextRefresher contextRefresher, ServiceConfigInfo serviceConfigInfo) {
        this.contextRefresher = contextRefresher;
        this.serviceConfigInfo = serviceConfigInfo;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public RefreshResolver refreshResolver() {
        RefreshResolver refreshResolver = new RefreshResolver();
        refreshResolver.setContextRefresher(contextRefresher);
        return refreshResolver;
    }

    @Bean
    public ConfigFileManager configFileManager() {
        ConfigFileManager configFileManager = new ConfigFileManager();
        configFileManager.setResolver(refreshResolver());
        configFileManager.setApplicationContext(applicationContext);
        return configFileManager;
    }

    @Bean
    public LocalMonitor localMonitor() {
        ConfigFileManager manager = configFileManager();
        LocalMonitor localMonitor = new LocalMonitor()
            .setManager(manager)
            .setServiceConfigInfo(serviceConfigInfo);
        try {
            // 创建配置文件监听线程
            WatchService service = FileSystems.getDefault().newWatchService();
            localMonitor.setService(service);
            ExecutorService localMonitorExecutor = Executors.newSingleThreadExecutor();
            localMonitorExecutor.execute(localMonitor);

            // 创建配置刷新线程 (定时刷新-3秒每次-无限循环)
            ScheduledExecutorService refreshCheckerExecutor = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
            refreshCheckerExecutor.scheduleAtFixedRate(() -> {
                try {
                    manager.applyChanges();
                } catch (Exception e) {
                    log.error("RefreshChecker执行失败", e);
                }
            }, 0, 3, TimeUnit.SECONDS);
        } catch (IOException e) {
            log.error("初始化LocalMonitor/配置刷新线程失败", e);
        }
        return localMonitor;
    }
}
