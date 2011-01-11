package net.bodz.bas.vfs.path;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.vfs.util.FeatureOption;

public class PathFormat
        implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The charset to use.
     */
    public static final String KEY_ENCODE_CHARSET = "encode.charset";

    /**
     * Bit flags to specify which characters will be encoded.
     */
    public static final String KEY_ENCODE_OPTIONS = "encode.option";
    public static final int ENCODE_SYMBOLS = 1;
    public static final int ENCODE_ASIAN = 2;
    public static final int ENCODE_ALL = -1;

    /**
     * Whether space (%20) should be format as '+' in URL.
     */
    public static final String KEY_SPACE = "space";
    public static final int SPACE_DEFAULT = 0;
    public static final int SPACE_PRESERVED = 1;
    public static final int SPACE_PLUS = 2;

    /**
     * Boolean value indicates whether username and password should be hidden from the path.
     */
    public static final String KEY_DISPLAY_SAFE = "displaysafe";

    private Charset encodeCharset = Charset.defaultCharset();
    private int encodeOptions = 0;

    private boolean displaySafe = false;

    private int spaceMode = SPACE_DEFAULT;

    private Map<Object, Object> attributes;

    public PathFormat(FeatureOption... options) {
        attributes = new TreeMap<Object, Object>();
        populate(options);
    }

    public void populate(FeatureOption... options) {
        for (FeatureOption opt : options) {
            Object key = opt.getKey();
            Object val = opt.getValue();

            if (KEY_ENCODE_CHARSET.equals(key))
                this.encodeCharset = (Charset) val;
            else if (KEY_ENCODE_OPTIONS.equals(key))
                this.encodeOptions = ((Integer) val).intValue();
            else if (KEY_SPACE.equals(key))
                this.spaceMode = ((Integer) val).intValue();
            else if (KEY_DISPLAY_SAFE.equals(key))
                this.displaySafe = ((Boolean) val).booleanValue();
            else
                this.attributes.put(key, val);
        }
    }

    public Charset getEncodeCharset() {
        return encodeCharset;
    }

    public int getEncodeOptions() {
        return encodeOptions;
    }

    public int getSpaceMode() {
        return spaceMode;
    }

    public boolean isDisplaySafe() {
        return displaySafe;
    }

    public Object getAttribute(Object key) {
        return attributes.get(key);
    }

}
