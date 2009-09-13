package net.bodz.bas.text.locale;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.bodz.bas.lang.err.UnexpectedException;

public class ResourceBundleDict extends _NLSDict {

    private final ResourceBundle bundle;

    public ResourceBundleDict(String title) {
        super(title, ThreadDict.getInstance());
        bundle = ResourceBundle.getBundle(getClass().getName());
    }

    public ResourceBundleDict(String title, ResourceBundle resourceBundle) {
        this(title, resourceBundle, ThreadDict.getInstance());
    }

    public ResourceBundleDict(String title, ResourceBundle resourceBundle, NLSDict next) {
        super(title, next);
        if (resourceBundle == null)
            throw new NullPointerException("resourceBundle");
        this.bundle = resourceBundle;
    }

    @Override
    protected boolean _contains(String key) {
        return bundle.containsKey(key);
    }

    @Override
    protected Object _get(String key, Object def) {
        if (!bundle.containsKey(key))
            return def;
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            assert bundle.containsKey(key);
            throw new UnexpectedException(e);
        }
    }

}
