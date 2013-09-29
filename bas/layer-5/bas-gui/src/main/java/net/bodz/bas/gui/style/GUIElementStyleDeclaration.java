package net.bodz.bas.gui.style;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;

public class GUIElementStyleDeclaration
        extends MappedGUIElementStyleDeclaration {

    private IGUIElementStyleDeclaration parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public GUIElementStyleDeclaration(IGUIElementStyleDeclaration parent) {
        setParent(parent);
    }

    @Override
    public IGUIElementStyleDeclaration getParent() {
        return parent;
    }

    /**
     * @throws ClassCastException
     *             If the parent isn't a {@link IGUIElementStyleDeclaration}.
     */
    @Override
    public void setParent(ICss3StyleDeclaration _parent) {
        IGUIElementStyleDeclaration parent = (IGUIElementStyleDeclaration) _parent;
        if (parent == null)
            parent = new StaticGUIStyleDeclaration();
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

    @Override
    public String getViewId() {
        // TODO
        return null;
    }

}
