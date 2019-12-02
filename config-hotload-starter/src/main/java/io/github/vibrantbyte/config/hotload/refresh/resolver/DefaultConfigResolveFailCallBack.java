package io.github.vibrantbyte.config.hotload.refresh.resolver;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author xiaoyueya
 */
@Slf4j
public class DefaultConfigResolveFailCallBack implements IConfigResolveFailCallback {

    /**
     * 暂未实现，only print
     */
    @Override
    public void callBack(String fileName, String message) {
        log.error("Config Manger Resolve File:{}, Failed message: {}", fileName, message);
    }

    private static final class SingletonHolder {
        private static final DefaultConfigResolveFailCallBack manager = new DefaultConfigResolveFailCallBack();
    }

    public static DefaultConfigResolveFailCallBack getInstance() {
        return SingletonHolder.manager;
    }

}
