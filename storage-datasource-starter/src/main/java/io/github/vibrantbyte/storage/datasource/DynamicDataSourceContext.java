package io.github.vibrantbyte.storage.datasource;

import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;

/**
 * @author vibrant byte
 */
public class DynamicDataSourceContext {

    private static final ThreadLocal<String> currentGroupName = new ThreadLocal<>();

    public static void clear() {
        currentGroupName.remove();
        HintManagerHolder.clear();
    }

    public static String getCurrentGroupName() {
        return currentGroupName.get();
    }

    public static void setCurrentGroupName(String name) {
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
