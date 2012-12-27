package net.bodz.bas.gui.style;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public class GUIStyleClass
        extends MappedGUIStyleClass {

    private IGUIStyleClass parent;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public GUIStyleClass(IGUIStyleClass parent) {
        setParent(parent);
    }

    @Override
    public IGUIStyleClass getParent() {
        return parent;
    }

    /**
     * @throws ClassCastException
     *             If the parent isn't a {@link IGUIStyleClass}.
     */
    @Override
    public void setParent(ICss3StyleClass _parent) {
        IGUIStyleClass parent = (IGUIStyleClass) _parent;
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

}
