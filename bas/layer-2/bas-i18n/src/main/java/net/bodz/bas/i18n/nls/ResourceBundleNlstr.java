package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.bodz.bas.c.java.util.UTF8ResourceBundle;

public class ResourceBundleNlstr
        extends AbstractNlstr {

    private final String baseName;

    private ResourceBundle resourceBundle;

    ResourceBundleNlstr(INlsTranslator parent) {
        this(parent, Locale.getDefault());
    }

    ResourceBundleNlstr(INlsTranslator parent, Locale locale) {
        super(parent, locale);
        this.baseName = getClass().getName();
        reload();
    }

    public ResourceBundleNlstr(INlsTranslator parent, String baseName) {
        this(parent, baseName, Locale.getDefault());
    }

    public ResourceBundleNlstr(INlsTranslator parent, String baseName, Locale locale) {
        this(parent, baseName, UTF8ResourceBundle.getBundle(baseName, locale));
    }

    protected ResourceBundleNlstr(INlsTranslator parent, String baseName, ResourceBundle resourceBundle) {
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
        try {
            this.resourceBundle = UTF8ResourceBundle.getBundle(baseName, locale);
        } catch (MissingResourceException e) {
            this.resourceBundle = null;
        }
    }

    @Override
    protected String localName() {
        return "rb:" + baseName;
    }

    /**
     * @param key
     *            The resource key, non-<code>null</code>.
     */
    @Override
    protected boolean localContainsKey(Object key) {
        if (resourceBundle == null)
            return false;
        return resourceBundle.containsKey(key.toString());
    }

    /**
     * @param key
     *            The resource key, non-<code>null</code>.
     */
    @Override
    protected Object localGet(Object key, Object def) {
        if (resourceBundle == null)
            return def;
        String skey = key.toString();
        if (resourceBundle.containsKey(skey))
            return resourceBundle.getString(skey);
        else
            return def;
    }

}
