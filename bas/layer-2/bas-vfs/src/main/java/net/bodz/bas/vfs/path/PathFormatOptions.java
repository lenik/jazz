package net.bodz.bas.vfs.path;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.exceptions.ReadOnlyException;

public class PathFormatOptions
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Charset charset;

    public static final int ENCODE_DISABLED = 0;
    public static final int ENCODE_NON_ALNUM = 1;
    public static final int ENCODE_PUNCTUATION = 2;
    public static final int ENCODE_ALL = 3;
    private int encodeOption;

    // Used by URL
    private boolean displaySafe;
    private boolean usingPrettySpace;

    // 
    private Map<String, Object> userParameters;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /**
     * @see #ENCODE_DISABLED
     * @see #ENCODE_NON_ALNUM
     * @see #ENCODE_PUNCTUATION
     * @see #ENCODE_ALL
     */
    public int getEncodeOption() {
        return encodeOption;
    }

    public void setEncodeOption(int encodeOption) {
        this.encodeOption = encodeOption;
    }

    public boolean shouldEncode(char c) {
        switch (encodeOption) {
        case ENCODE_DISABLED:
            return false;
        case ENCODE_NON_ALNUM:
            return Character.isLetterOrDigit(c);
        case ENCODE_PUNCTUATION:
            return !Character.isLetter(c);
        case ENCODE_ALL:
        default:
            return true;
        }
    }

    public boolean isDisplaySafe() {
        return displaySafe;
    }

    public void setDisplaySafe(boolean displaySafe) {
        this.displaySafe = displaySafe;
    }

    public boolean isUsingPrettySpace() {
        return usingPrettySpace;
    }

    public void setUsingPrettySpace(boolean usingPrettySpace) {
        this.usingPrettySpace = usingPrettySpace;
    }

    public Object getUserParameter(String key) {
        if (userParameters == null)
            return null;
        return userParameters.get(key);
    }

    public synchronized void setUserParameter(String key, Object parameter) {
        if (userParameters == null)
            userParameters = new HashMap<String, Object>();
        userParameters.put(key, parameter);
    }

    public PathFormatOptions toUnmodifiable() {
        return new Unmodifiable();
    }

    class Unmodifiable
            extends PathFormatOptions {

        private static final long serialVersionUID = 1L;

        @Override
        public void setCharset(Charset charset) {
            throw new ReadOnlyException();
        }

        @Override
        public void setDisplaySafe(boolean displaySafe) {
            throw new ReadOnlyException();
        }

        @Override
        public void setEncodeOption(int encodeOption) {
            throw new ReadOnlyException();
        }

        @Override
        public synchronized void setUserParameter(String key, Object parameter) {
            throw new ReadOnlyException();
        }

        @Override
        public void setUsingPrettySpace(boolean usingPrettySpace) {
            throw new ReadOnlyException();
        }

        @Override
        public PathFormatOptions toUnmodifiable() {
            return this;
        }

    }

}
