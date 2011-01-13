package net.bodz.bas.i18n.nls;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This class defines runtime extension for the resource bundle.
 * <p>
 * The {@link #getKeys()} method defined in this class provides the additional resource keys .
 */
public class RBUse_zh_CN_runtime
        extends ResourceBundle {

    Properties properties;

    public RBUse_zh_CN_runtime() {
        properties = new Properties();
        properties.setProperty("key3", "zh_CN_runtime3");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Enumeration<String> getKeys() {
        Enumeration<?> keys = properties.keys();
        return (Enumeration<String>) keys;
    }

    @Override
    protected Object handleGetObject(String key) {
        return properties.get(key);
    }

}
