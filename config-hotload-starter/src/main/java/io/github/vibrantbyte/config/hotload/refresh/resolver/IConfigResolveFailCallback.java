package io.github.vibrantbyte.config.hotload.refresh.resolver;


/**
 * @author vibrant byte
 */
public interface IConfigResolveFailCallback {

    /**
     * 配置解析回调处理
     * @param fileName
     * @param message
     */
    void callBack(String fileName, String message);
}
