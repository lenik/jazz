package net.bodz.bas.a;

import java.util.HashMap;

public class DocumentAttributes extends HashMap<Object, Object> {

    private static final long serialVersionUID = -2841651853021730975L;

    protected final Class<?> clazz;

    public DocumentAttributes(Class<?> clazz) {
        this.clazz = clazz;
    }

    /** Display name */
    public static final String NAME = "name"; //$NON-NLS-1$
    public static final String DESCRIPTION = "description"; //$NON-NLS-1$
    public static final String HELPKEY = "helpkey"; //$NON-NLS-1$

    public static final String AUTHOR = "author"; //$NON-NLS-1$
    public static final String COMPANY = "company"; //$NON-NLS-1$
    public static final String WEBSITE = "website"; //$NON-NLS-1$
    public static final String EMAIL = "email"; //$NON-NLS-1$

    public static final String ICON = "icon"; //$NON-NLS-1$
    public static final String ICON_DISABLED = "icon_d"; //$NON-NLS-1$
    public static final String ICON_HOVER = "icon_h"; //$NON-NLS-1$

    public static final String COLOR = "color"; //$NON-NLS-1$
    public static final String BACKCOLOR = "backcolor"; //$NON-NLS-1$
    public static final String FONT = "font"; //$NON-NLS-1$

    public static final String VERSION = "version"; //$NON-NLS-1$
    public static final String CREATED_TIME = "createdTime"; //$NON-NLS-1$
    public static final String MODIFIED_TIME = "modifiedTime"; //$NON-NLS-1$

    public static final String ADDRESS = "address"; //$NON-NLS-1$
    public static final String CITY = "city"; //$NON-NLS-1$
    public static final String PROVINCE = "province"; //$NON-NLS-1$
    public static final String COUNTRY = "country"; //$NON-NLS-1$
    public static final String PHONE = "phone"; //$NON-NLS-1$
    public static final String MOBILE = "mobile"; //$NON-NLS-1$
    public static final String FAX = "fax"; //$NON-NLS-1$

}
