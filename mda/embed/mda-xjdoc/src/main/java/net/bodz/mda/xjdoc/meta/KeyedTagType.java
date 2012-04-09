package net.bodz.mda.xjdoc.meta;

import javax.free.Pair;

public abstract class KeyedTagType
        extends AbstractTagType {

    @Override
    public Object parse(String text) {
        int spc = -1;
        text = text.trim();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isWhitespace(ch)) {
                spc = i;
                break;
            }
        }

        String keyText;
        String valueText;
        if (spc == -1) {
            keyText = text;
            valueText = "";
        } else {
            keyText = text.substring(0, spc);
            valueText = text.substring(spc + 1).trim();
        }

        Object key = parseKey(keyText);
        Object value = parseValue(valueText);
        return new Pair<Object, Object>(key, value);
    }

    @Override
    public String format(Object key, Object value) {
        String keyText = formatKey(key);
        String valueText = formatValue(value);
        return keyText + " " + valueText;
    }

    protected Object parseKey(String keyText) {
        return keyText;
    }

    protected String formatKey(Object key) {
        if (key == null)
            return null;
        else
            return key.toString();
    }

    protected abstract Object parseValue(String valueText);

    protected abstract String formatValue(Object value);

}
