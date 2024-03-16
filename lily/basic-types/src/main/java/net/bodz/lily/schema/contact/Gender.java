package net.bodz.lily.schema.contact;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefJsonFn;
import net.bodz.bas.t.predef.PredefMetadata;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

/**
 * Birth sex
 *
 * @label.zh 性别
 */
public class Gender
        extends Predef<Gender, Character> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<Gender, Character> meta = PredefMetadata.forClass(Gender.class);

    private Gender(char val, String name) {
        super(val, name, meta);
    }

    public static final char K_FEMALE = 'f';
    public static final char K_MALE = 'm';
    public static final char K_INTERSEX = 'i';
    public static final char K_NEUTRAL = 'n'; // asexual
    public static final char K_MTF = 'y';
    public static final char K_FTM = 'x';
    public static final char K_HRTF = 'F'; // GAHT/HRT, xyn, 神楽坂
    public static final char K_HRTM = 'M';
    public static final char K_QUEER = 'q';
    public static final char K_OTHER = 'o';

    /**
     * 女
     */
    public static final Gender FEMALE = new Gender(K_FEMALE, "female");

    /**
     * 男
     */
    public static final Gender MALE = new Gender(K_MALE, "male");

    /**
     * 双性
     */
    public static final Gender INTERSEX = new Gender(K_INTERSEX, "intersex");

    /**
     * 无性
     */
    public static final Gender NEUTRAL = new Gender(K_NEUTRAL, "neutral");

    /**
     * 变性女
     */
    public static final Gender MTF = new Gender(K_MTF, "MtF");

    /**
     * 变性男
     */
    public static final Gender FTM = new Gender(K_FTM, "FtM");

    /**
     * 药娘
     */
    public static final Gender HRTF = new Gender(K_HRTF, "HRT female");

    /**
     * 药郎
     */
    public static final Gender HRTM = new Gender(K_HRTM, "HRT male");

    /**
     * 酷儿
     */
    public static final Gender QUEER = new Gender(K_QUEER, "queer");

    /**
     * 其它
     */
    public static final Gender OTHER = new Gender(K_OTHER, "other");

    static {
        IXjdocAware.fn.injectFields(Gender.class, false);
    }

    public static Gender jsonGet(JsonObject o, String propertyName)
            throws ParseException {
        Object any = o.get(propertyName);
        return new PredefJsonFn<>(Gender.class)//
                .property(propertyName) //
                .parseAny(any);
    }

    public static Gender jsonGet(JsonObject o, String propertyName, Gender defaultValue) {
        Object any = o.get(propertyName);
        return new PredefJsonFn<>(Gender.class)//
                .property(propertyName) //
                .parseAny(any, defaultValue);
    }

}
