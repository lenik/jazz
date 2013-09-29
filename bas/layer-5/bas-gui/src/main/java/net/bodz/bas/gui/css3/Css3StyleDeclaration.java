package net.bodz.bas.gui.css3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Css3StyleDeclaration
        extends MappedCss3StyleDeclaration {

    private ICss3StyleDeclaration parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public Css3StyleDeclaration(ICss3StyleDeclaration parent) {
        setParent(parent);
    }

    @Override
    public ICss3StyleDeclaration getParent() {
        return parent;
    }

    @Override
    public void setParent(ICss3StyleDeclaration parent) {
        if (parent == null)
            parent = new StaticCss3StyleDeclaration();
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
