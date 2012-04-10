package net.bodz.mda.xjdoc.meta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class KeyedTagType
        extends AbstractTagType {

    @Override
    public Object parse(Object cont, String string) {
        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        String keyText = WordTokenizer.firstWord(string);
        String valueText = string.substring(keyText.length());
        keyText = keyText.trim();
        valueText = valueText.trim();

        Object key = parseKey(keyText);
        Object valueCont = map.get(key);
        Object value = parseValue(valueCont, valueText);
        // return new Pair<Object, Object>(key, value);
        if (valueCont == null)
            map.put(key, value);
        return map;
    }

    @Override
    public String[] format(Object value) {
        Map<?, ?> map = (Map<?, ?>) value;
        String[] array = new String[map.size()];

        int index = 0;
        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            String keyText = formatKey(k);
            String valueText = formatValue(v);
            array[index++] = keyText + " " + valueText;
        }
        return array;
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

    protected abstract Object parseValue(Object cont, String valueString);

    protected abstract String formatValue(Object value);

}
