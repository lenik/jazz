package net.bodz.mda.xjdoc.meta;

public class StringTagType
        extends AbstractTagType {

    @Override
    public Object parse(Object cont, String string) {
        return string;
    }

    @Override
    public String[] format(Object value) {
        if (value == null)
            return new String[0];
        else
            return new String[] { value.toString() };
    }

    public static final StringTagType INSTANCE = new StringTagType();

}
