package io.github.vibrantbyte.config.hotload.refresh.resolver;


/**
 * @author vibrant byte
 */
public interface IConfigResolveFailCallback {

    /**
     * 配置解析回调处理
     * @param fileName 文件名称
     * @param message 信息
     */
    void callBack(String fileName, String message);
}
