package net.bodz.bas.meta.build;

import java.util.HashMap;

public class DocumentAttributes
        extends HashMap<Object, Object> {

    private static final long serialVersionUID = -2841651853021730975L;

    protected final Class<?> clazz;

    public DocumentAttributes(Class<?> clazz) {
        this.clazz = clazz;
    }

    /** Display name */
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String HELPKEY = "helpkey";

    public static final String AUTHOR = "author";
    public static final String COMPANY = "company";
    public static final String WEBSITE = "website";
    public static final String EMAIL = "email";

    public static final String ICON = "icon";
    public static final String ICON_DISABLED = "icon_d";
    public static final String ICON_HOVER = "icon_h";

    public static final String COLOR = "color";
    public static final String BACKCOLOR = "backcolor";
    public static final String FONT = "font";

    public static final String VERSION = "version";
    public static final String CREATED_TIME = "createdTime";
    public static final String MODIFIED_TIME = "modifiedTime";

    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String COUNTRY = "country";
    public static final String PHONE = "phone";
    public static final String MOBILE = "mobile";
    public static final String FAX = "fax";

}
