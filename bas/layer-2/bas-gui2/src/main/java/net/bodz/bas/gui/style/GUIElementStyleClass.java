package net.bodz.bas.gui.style;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public class GUIElementStyleClass
        extends MappedGUIElementStyleClass {

    private IGUIElementStyleClass parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public GUIElementStyleClass(IGUIElementStyleClass parent) {
        setParent(parent);
    }

    @Override
    public IGUIElementStyleClass getParent() {
        return parent;
    }

    /**
     * @throws ClassCastException
     *             If the parent isn't a {@link IGUIElementStyleClass}.
     */
    @Override
    public void setParent(ICss3StyleClass _parent) {
        IGUIElementStyleClass parent = (IGUIElementStyleClass) _parent;
        if (parent == null)
            parent = new StaticGUIStyleClass();
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
