package net.bodz.bas.text.locale;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.bodz.bas.lang.err.UnexpectedException;

public class ResourceBundleDict extends _NLSDict {

    private NLSDict              parent;
    private final ResourceBundle bundle;

    public ResourceBundleDict(String title) {
        super(title);
        parent = ThreadDict.getInstance();
        bundle = ResourceBundle.getBundle(getClass().getName());
    }

    public ResourceBundleDict(String title, ResourceBundle resourceBundle) {
        this(ThreadDict.getInstance(), title, resourceBundle);
    }

    public ResourceBundleDict(NLSDict parent, String title, ResourceBundle resourceBundle) {
        super(title);
        if (resourceBundle == null)
            throw new NullPointerException("resourceBundle");
        this.parent = parent;
        this.bundle = resourceBundle;
    }

    @Override
    public Object get(String key, Object def) {
        if (!bundle.containsKey(key))
            if (parent != null)
                return parent.get(key, def);
            else
                return def;
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            throw new UnexpectedException(e);
        }
    }

}
