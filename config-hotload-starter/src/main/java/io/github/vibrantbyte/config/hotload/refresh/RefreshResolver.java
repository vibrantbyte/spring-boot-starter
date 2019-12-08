package io.github.vibrantbyte.config.hotload.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.refresh.ContextRefresher;

/**
 *
 * @author vibrant byte
 *
 */
@Slf4j(topic = "hot-load-starter")
public class RefreshResolver {

    private ContextRefresher contextRefresher;

    public void resolve(int times) {
        try {
            log.info("[{}times]hot-load-starter Spring refresh 组件刷新！！！",times);
            contextRefresher.refresh();
        } catch (Exception e) {
            log.error("[{}times]刷新失败,e={}", e);
        }
    }

    public void setContextRefresher(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

}
