package net.bodz.bas.vfs.path;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.java.nio.Charsets;

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

    /**
     * Boolean value indicates whether username and password should be hidden from the path.
     */
    public static final String KEY_DISPLAY_SAFE = "displaysafe";

    private boolean locked;

    private Charset encodeCharset = Charsets.UTF_8; // Charset.defaultCharset();
    private int encodeOptions;
    private PathSpaceMode spaceMode = PathSpaceMode.normalized;
    private boolean displaySafe;
    private boolean query = true;
    private boolean range;

    private Map<String, Object> attributes;

    public PathFormat() {
        attributes = new TreeMap<String, Object>();
    }

    public PathFormat(PathFormat baseFormat) {
        if (baseFormat == null)
            throw new NullPointerException("baseFormat");
        encodeCharset = baseFormat.encodeCharset;
        encodeOptions = baseFormat.encodeOptions;
        displaySafe = baseFormat.displaySafe;
        spaceMode = baseFormat.spaceMode;
        attributes = new TreeMap<String, Object>(baseFormat.attributes);
    }

    public void lock() {
        locked = true;
    }

    protected void checkLock() {
        if (locked)
            throw new IllegalStateException("The state of this object is locked.");
    }

    public Charset getEncodeCharset() {
        return encodeCharset;
    }

    public void setEncodeCharset(Charset charset) {
        checkLock();
        if (charset == null)
            throw new NullPointerException("charset");
        this.encodeCharset = charset;
    }

    public int getEncodeOptions() {
        return encodeOptions;
    }

    public void setEncodeOptions(int encodeOptions) {
        checkLock();
        this.encodeOptions = encodeOptions;
    }

    public PathSpaceMode getSpaceMode() {
        return spaceMode;
    }

    public void setSpaceMode(PathSpaceMode spaceMode) {
        checkLock();
        if (spaceMode == null)
            throw new NullPointerException("spaceMode");
        this.spaceMode = spaceMode;
    }

    public boolean isDisplaySafe() {
        return displaySafe;
    }

    public void setDisplaySafe(boolean displaySafe) {
        checkLock();
        this.displaySafe = displaySafe;
    }

    public boolean isQuery() {
        return query;
    }

    public void setQuery(boolean query) {
        checkLock();
        this.query = query;
    }

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        checkLock();
        this.range = range;
    }

    public Map<String, ?> getAttributeMap() {
        return attributes;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, Object attribute) {
        checkLock();
        attributes.put(key, attribute);
    }

    public Object removeAttribute(String key) {
        checkLock();
        return attributes.remove(key);
    }

}
