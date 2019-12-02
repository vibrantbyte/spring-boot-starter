package io.github.vibrantbyte.config.hotload.refresh.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vibrant byte
 * @date 2017/6/13
 */
public enum FileExtension {

    /**
     * xml 类型
     */
    XML_FILE("xml"),
    YML_FILE("yml"),
    YAML_FILE("yaml"),
    PROPERTIES_FILE("properties"),
    NO_EXTENSION_FILE(""),
    JSON_EXTENSION_FILE("json"),
    JS_EXTENSION_FILE("js"),
    NO_IDENTIFY_EXTENSION_FILE("*");

    private String extension;

    private static final Map<String, FileExtension> supportExtensions = new HashMap<>(7);

    static {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension == FileExtension.NO_IDENTIFY_EXTENSION_FILE) {
                continue;
            }
            supportExtensions.put(fileExtension.getExtension(), fileExtension);
        }
    }

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public static Map<String, FileExtension> getSupportExtensions() {
        return supportExtensions;
    }
}
