package io.github.vibrantbyte.storage.datasource;

import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vibrant byte
 */
@Slf4j
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> currentGroupName = new ThreadLocal<>();

    public static void clear() {
        currentGroupName.remove();
        HintManagerHolder.clear();
    }

    public static String getCurrentGroupName() {
        String sourceGroup = currentGroupName.get();
        log.info("storage-datasource-starter 切换数据源为：{}",sourceGroup);
        return sourceGroup;
    }

    public static void setCurrentGroupName(String name) {
        log.info("storage-datasource-starter 设置数据源为：{}",name);
        currentGroupName.set(name);
    }

    public static void setMasterRouteOnly(boolean isMasterRouteOnly) {
        if (isMasterRouteOnly) {
            HintManager hintManager = HintManager.getInstance();
            hintManager.setMasterRouteOnly();
        } else {
            HintManagerHolder.clear();
        }
    }

}
