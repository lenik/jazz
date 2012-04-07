package net.bodz.mda.xjdoc.meta;

public class DocTagType
        extends AbstractTagType {

    @Override
    public Object parse(String text) {
        return text;
    }

    @Override
    public String format(Object key, Object value) {
        if (value == null)
            return null;
        else
            return value.toString();
    }

}
