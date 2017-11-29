package net.bodz.bas.err;

import java.util.Map;

public class DuplicatedKeyException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public DuplicatedKeyException() {
        super();
    }

    public DuplicatedKeyException(Map<?, ?> map, Object key, String hint) {
        super(String.format("%s %s is already used, the old value is %s.", //
                hint, key, map.get(key)));
    }

    public DuplicatedKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedKeyException(String s) {
        super(s);
    }

    public DuplicatedKeyException(Throwable cause) {
        super(cause);
    }

}
