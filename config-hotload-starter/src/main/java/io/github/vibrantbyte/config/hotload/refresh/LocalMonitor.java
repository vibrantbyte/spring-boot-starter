package io.github.vibrantbyte.config.hotload.refresh;

import io.github.vibrantbyte.config.hotload.refresh.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 *
 * @author vibrant byte
 *
 */
@Slf4j(topic = "hot-load-starter")
public class LocalMonitor implements Runnable {

    private static final List<Kind<Path>> changedEvents = Arrays.asList(ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

    private volatile boolean isStop = false;

    private ConfigFileManager manager;

    private ServiceConfigInfo serviceConfigInfo;

    private WatchService service;

    @Override
    public void run() {
        try {
            log.info("目录监控开始运行,Thread is running ,name={}", LocalMonitor.class.getSimpleName());
            watchFile();
        } catch (Exception e) {
            log.error("LocalMonitor执行失败，e={}", e);
        }
    }

    private void watchFile() {
        try {
            register(collectAll());
            while (!isStop) {
                // wait for key to be signalled
                WatchKey key = service.take();
                List<WatchEvent<?>> events = key.pollEvents();
                List<String> eventList = new ArrayList<>();
                for (WatchEvent event : events) {
                    eventList.add(event.kind().name());
                    if (event.kind() == OVERFLOW) {
                        continue;
                    }

                    //The filename is the context of the event.
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    String filename = ev.context().getFileName().toString();
                    // 变化的文件不在监控的范围内；
                    if (!serviceConfigInfo.getFileExtensions().contains(FileUtil.getExtension(filename))) {
                        log.info("非受监控的文件发生变更，无需刷新");
                        continue;
                    }

                    if (changedEvents.contains(ev.kind())) {
                        manager.addChange(filename, ev.kind());
                        log.info("文件有修改, file={}, way={}", filename, ev.kind());
                    }
                }

                /*
                 * 无法恢复继续等待的状态时
                 */
                if (!key.reset()) {
                    break;
                }

            }
        } catch (Exception e) {
            log.error("watchFile执行失败，e=", e);
        }

    }

    private Set<String> collectAll() throws IOException {
        Set<String> monitorDirs = new HashSet<>();
        List<String> configSavePaths = serviceConfigInfo.getConfigSavePath();
        if (configSavePaths.isEmpty()) {
            log.info("没有需要监控的配置文件");
            return Collections.emptySet();
        }

        // 根据提供的目录检测该目录下对应的文件后缀
        for (String configSavePath : configSavePaths) {
            File file = new File(configSavePath);
            if (file.isDirectory()) {
                // 逐个监听包含配置文件后缀的子目录
                monitorDirs.addAll(collectDir(file));
            } else if (file.isFile()) {
                monitorDirs.add(file.getParent());
                log.info("文件需要搜集:file={},dir={}", file.getAbsolutePath(), file.getParent());
            }
        }

        return monitorDirs;
    }

    private Set<String> collectDir(File directory) throws IOException {
        Set<String> dirs = new HashSet<>();
        if (null == directory || !directory.isDirectory()) {
            return Collections.emptySet();
        }

        File[] files = directory.listFiles();

        if (null == files || files.length <= 0) {
            log.info("目录需要搜集，且为空空,dir={}", directory);
            dirs.add(directory.getAbsolutePath());
            return dirs;
        }

        boolean isConfigDir = false;
        for (File file : files) {
            if (file.isDirectory()) {
                dirs.addAll(collectDir(file));
                continue;
            }

            if (serviceConfigInfo.getFileExtensions().contains(FileUtil.getExtension(file.getName()))) {
                isConfigDir = true;
            }
        }

        if (isConfigDir) {
            log.info("目录需要搜集,dir={}", directory);
            dirs.add(directory.getAbsolutePath());
        }
        return dirs;
    }

    private void register(Set<String> dirs) {
        if (dirs.isEmpty()) {
            return;
        }

        dirs.forEach(dir -> {
            try {
                Paths.get(dir).register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                log.info("成功监控目录,dir=" + dir);
            } catch (IOException e) {
                log.info("监控目录失败,dir={},e={}", dir, e);
            }
        });
        dirs.clear();
    }


    public void close() {
        try {
            this.service.close();
        } catch (IOException e) {
            log.error("关闭WatchService失败，e={}", e);
        }
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

    public LocalMonitor setManager(ConfigFileManager manager) {
        this.manager = manager;
        return this;
    }

    public LocalMonitor setServiceConfigInfo(ServiceConfigInfo serviceConfigInfo) {
        this.serviceConfigInfo = serviceConfigInfo;
        return this;
    }

    public LocalMonitor setService(WatchService service) {
        this.service = service;
        return this;
    }

}
