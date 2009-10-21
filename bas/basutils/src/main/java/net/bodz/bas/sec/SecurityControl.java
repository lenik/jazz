package net.bodz.bas.sec;

public class SecurityControl extends SecurityException {

    private static final long serialVersionUID = -1493647695398433533L;

    public static final int   EXIT             = 1;

    private final int         code;
    private final Object      value;

    public SecurityControl(int code, Object value) {
        super();
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public Object getValue() {
        return value;
    }

}
