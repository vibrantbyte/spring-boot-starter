package io.github.vibrantbyte.config.hotload.refresh;

import io.github.vibrantbyte.config.hotload.refresh.utils.FileExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 * @author vibrant byte
 *
 */
@ConfigurationProperties("spring.config")
@Slf4j(topic = "hot-load-starter")
public class ServiceConfigInfo {

    private String location;

    private String fileExtension;

    private List<String> configFileDir = null;

    private List<FileExtension> fileExtensions = null;

    private List<FileExtension> fileExtensionsDefault = Arrays.asList(FileExtension.YML_FILE, FileExtension.YAML_FILE, FileExtension.PROPERTIES_FILE, FileExtension.XML_FILE);

    public List<FileExtension> getFileExtensions() {
        if (null == fileExtensions) {
            this.initFileExtensions();
        }
        return fileExtensions;
    }

    public List<String> getConfigSavePath() {
        if (null == configFileDir) {
            this.initConfigFileDir();
        }
        return configFileDir;
    }

    private void initConfigFileDir() {
        if (StringUtils.isEmpty(location) || StringUtils.trimAllWhitespace(location).equals("")) {
            configFileDir = Collections.emptyList();
            return;
        }
        configFileDir = Arrays.asList(StringUtils.trimArrayElements(StringUtils.commaDelimitedListToStringArray(location)));
    }

    private void initFileExtensions() {
        if (StringUtils.isEmpty(fileExtension) || StringUtils.trimAllWhitespace(fileExtension).equals("")) {
            fileExtensions = fileExtensionsDefault;
            return;
        }

        String[] extensions = StringUtils.trimArrayElements(StringUtils.commaDelimitedListToStringArray(fileExtension));
        if (extensions.length <= 0) {
            fileExtensions =  Collections.emptyList();
            return;
        }

        Map<String, FileExtension> supportExtensions = FileExtension.getSupportExtensions();
        fileExtensions = new ArrayList<>();
        for (String extension : extensions) {
            if (supportExtensions.containsKey(extension)) {
                fileExtensions.add(supportExtensions.get(extension));
            }
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
