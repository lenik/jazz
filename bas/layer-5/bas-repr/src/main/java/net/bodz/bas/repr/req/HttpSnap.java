package net.bodz.bas.repr.req;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpSnap
        implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ATTRIBUTE_KEY = HttpSnap.class.getName();

    private final long id;
    private Map<String, Object> attributeMap;
    private Map<String, String[]> parameterMap;
    private final long creation = System.currentTimeMillis();
    private long lastmod = creation;

    public HttpSnap(long id) {
        this.id = id;
        this.attributeMap = new HashMap<>();
        this.parameterMap = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public void setAttribute(String key, Object value) {
        attributeMap.put(key, value);
        touch();
    }

    public void removeAttribute(String key) {
        attributeMap.remove(key);
        touch();
    }

    public void clearAttributes() {
        attributeMap.clear();
        touch();
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public String getParameter(String key) {
        String[] array = parameterMap.get(key);
        if (array == null || array.length == 0)
            return null;
        else
            return array[0];
    }

    public void touch() {
        lastmod = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creation;
    }

    public long getLastModifiedTime() {
        return lastmod;
    }

    public void merge(Map<String, Object[]> parameterMap) {
        for (Entry<String, Object[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("-"))
                continue;
            parameterMap.put(key, entry.getValue());
        }
    }

}
