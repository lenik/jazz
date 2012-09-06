package net.bodz.bas.i18n.nls;

public class Translation {

    public String _(String key) {
        return key;
    }

    public String _(String format, Object... args) {
        return String.format(format, args);
    }

}
