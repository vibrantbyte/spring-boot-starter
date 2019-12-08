package io.github.vibrantbyte.config.hotload.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author vibrant byte
 * @date 2017/6/13
 */
@Slf4j(topic = "hot-load-starter")
public class ConfigFileManager {

    /**
     * 刷新次数计数
     */
    private static final AtomicInteger REFRESH_TIMES = new AtomicInteger(0);

    private ApplicationContext applicationContext;

    private RefreshResolver resolver;

    private ConcurrentHashMap<String, WatchEvent.Kind<Path>> changes = new ConcurrentHashMap<>();

    public void addChange(String filename, WatchEvent.Kind<Path> kind) {
        changes.put(filename, kind);
    }

    public void applyChanges() {
        if (null == resolver) {
            log.info("由于resolver为空，无法执行refresh操作");
            return;
        }
        if (changes.size() <= 0) {
            log.debug("当前没有配置文件变更");
            return;
        }
        int times = REFRESH_TIMES.incrementAndGet();
        // 刷新配置，清空变更记录，广播配置重新加载事件(该事件用于做类似数据源配置更新后的连接重建)
        resolver.resolve(times);
        Set<String> files = changes.keySet();
        log.info("[{}times]变更文件: {}",times, files);
        Set<String> filesCopy = new HashSet<>();
        filesCopy.addAll(files);
        changes.clear();
        applicationContext.publishEvent(new ConfigReloadedEvent(this, filesCopy));
        log.info("[{}times]所有配置文件刷新完毕",times);
    }

    public void setResolver(RefreshResolver resolver) {
        this.resolver = resolver;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
