package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleNLS
        extends AbstractNLS {

    private final String baseName;

    private ResourceBundle resourceBundle;

    ResourceBundleNLS(NLS parent)
            throws MissingResourceException {
        this(parent, Locale.getDefault());
    }

    ResourceBundleNLS(NLS parent, Locale locale)
            throws MissingResourceException {
        super(parent, locale);
        this.baseName = getClass().getName();
        reload();
    }

    public ResourceBundleNLS(NLS parent, String baseName, ResourceBundle resourceBundle) {
        super(parent, resourceBundle.getLocale());
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.baseName = baseName;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public void reload()
            throws MissingResourceException {
        Locale locale = getPreferredLocale();
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }

    @Override
    protected String localName() {
        return "rb:" + baseName;
    }

    @Override
    protected boolean localContainsKey(String key) {
        return resourceBundle.containsKey(key);
    }

    @Override
    protected Object localGet(String key, Object def) {
        if (resourceBundle.containsKey(key))
            return resourceBundle.getString(key);
        else
            return def;
    }

}
