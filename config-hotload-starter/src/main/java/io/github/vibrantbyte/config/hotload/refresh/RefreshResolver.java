package io.github.vibrantbyte.config.hotload.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.refresh.ContextRefresher;

/**
 *
 * @author vibrant byte
 * @date 2017/6/13
 */
@Slf4j(topic = "hot-load-starter")
public class RefreshResolver {

    private ContextRefresher contextRefresher;

    public void resolve(String filename) {
        try {
            contextRefresher.refresh();
        } catch (Exception e) {
            log.error("刷新失败,e={}", e);
        }
    }

    public void resolve() {
        try {
            contextRefresher.refresh();
        } catch (Exception e) {
            log.error("刷新失败,e={}", e);
        }
    }

    public void setContextRefresher(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

}
