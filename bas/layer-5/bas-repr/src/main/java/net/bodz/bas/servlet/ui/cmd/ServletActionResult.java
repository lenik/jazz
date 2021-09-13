package net.bodz.bas.servlet.ui.cmd;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServletActionResult {

    private Object data;

    /** target -> url/href */
    private Map<String, String> reloadMap;

    /** selector -> html */
    private Map<String, String> updateMap;

    private List<Object> logs;

    public ServletActionResult() {
        reloadMap = new LinkedHashMap<String, String>();
        updateMap = new LinkedHashMap<String, String>();
        logs = new ArrayList<Object>();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getReloadMap() {
        return reloadMap;
    }

    public void reload(String target, String url) {
        reloadMap.put(target, url);
    }

    public Map<String, String> getUpdateMap() {
        return updateMap;
    }

    public void update(String selector, String content) {
        updateMap.put(selector, content);
    }

    public List<Object> getLogs() {
        return logs;
    }

}
