package net.bodz.bas.gui.css3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Css3StyleClass
        extends MappedCss3StyleClass {

    private final ICss3StyleClass parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public Css3StyleClass(ICss3StyleClass parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    public ICss3StyleClass getParent() {
        return parent;
    }

    @Override
    public String getProperty(String key) {
        return map.get(key);
    }

    @Override
    public void setProperty(String key, Object value) {
        if (value == null)
            map.remove(key);
        else
            map.put(key, value.toString());
    }

}
