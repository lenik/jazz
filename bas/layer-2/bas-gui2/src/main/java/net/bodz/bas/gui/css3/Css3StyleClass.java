package net.bodz.bas.gui.css3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Css3StyleClass
        extends MappedCss3StyleClass {

    private ICss3StyleClass parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public Css3StyleClass(ICss3StyleClass parent) {
        setParent(parent);
    }

    @Override
    public ICss3StyleClass getParent() {
        return parent;
    }

    @Override
    public void setParent(ICss3StyleClass parent) {
        if (parent == null)
            parent = new StaticCss3StyleClass();
        this.parent = parent;
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
