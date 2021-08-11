package net.bodz.lily.contact;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefJsonFn;
import net.bodz.bas.t.predef.PredefMetadata;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

/**
 * 性别
 */
public class Gender
        extends Predef<Gender, Character> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<Gender, Character> meta = PredefMetadata.forClass(Gender.class);

    private Gender(char val, String name) {
        super(val, name, meta);
    }

    public static final char K_UNKNOWN = '-';
    public static final char K_MALE = 'M';
    public static final char K_FEMALE = 'F';
    public static final char K_OTHER = 'X';

    /**
     * 未知
     */
    public static final Gender UNKNOWN = new Gender(K_UNKNOWN, "UNKNOWN");

    /**
     * 男
     */
    public static final Gender MALE = new Gender(K_MALE, "MALE");

    /**
     * 女
     */
    public static final Gender FEMALE = new Gender(K_FEMALE, "FEMALE");

    /**
     * 其它
     */
    public static final Gender OTHER = new Gender(K_OTHER, "OTHER");

    static {
        IXjdocAware.fn.injectFields(Gender.class, false);
    }

    public static Gender jsonGet(JsonObject o, String propertyName, Gender defaultValue)
            throws ParseException {
        return PredefJsonFn.jsonGet(o, propertyName, Gender.class, defaultValue);
    }

}
