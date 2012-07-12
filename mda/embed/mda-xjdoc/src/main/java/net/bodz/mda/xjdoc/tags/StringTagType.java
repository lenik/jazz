package net.bodz.mda.xjdoc.tags;

public class StringTagType
        extends ScalarTagType<String> {

    @Override
    protected String parse(String s) {
        return s;
    }

    @Override
    protected String format(String value) {
        return value;
    }

    static final StringTagType INSTANCE = new StringTagType();

    public static StringTagType getInstance() {
        return INSTANCE;
    }

}
