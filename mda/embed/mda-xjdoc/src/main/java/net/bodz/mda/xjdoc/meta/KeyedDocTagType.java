package net.bodz.mda.xjdoc.meta;

public class KeyedDocTagType
        extends KeyedTagType {

    @Override
    protected Object parseValue(String valueText) {
        return valueText;
    }

    @Override
    protected String formatValue(Object value) {
        if (value == null)
            return null;
        else
            return value.toString();
    }

}
