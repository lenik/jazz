package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.WordTokenizer;

class KeyedTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public KeyedTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
    }

    @Override
    public Object parseJavadoc(Object cont, String string) {
        String keyStr = WordTokenizer.firstWord(string);
        String valueStr = string.substring(keyStr.length());
        keyStr = keyStr.trim();
        valueStr = valueStr.trim();

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(keyStr);
        Object valueCont = map.get(key);
        Object value = valueTagType.parseJavadoc(valueCont, valueStr);
        if (valueCont != value)
            map.put(key, value);
        return map;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        Map<?, ?> map = (Map<?, ?>) value;
        String[] array = new String[map.size()];

        int index = 0;
        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            String keyStr = formatKey(k);
            for (String valueStr : valueTagType.formatJavadoc(v))
                array[index] = keyStr + " " + valueStr;
            index++;
        }
        return array;
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        // suffix -> suffix-1.suffix-rest
        Object key = parseKey(suffix);
        Object valueCont = map.get(key);
        Object value = valueTagType.parseAttribute(valueCont, null, string);
        // return new Pair<Object, Object>(key, value);
        if (valueCont == null)
            map.put(key, value);
        return map;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        Map<?, ?> map = (Map<?, ?>) value;
        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (v != null) {
                String keyStr = formatKey(k);
                valueTagType.writeAttributes(out, prefix + "." + keyStr, v);
            }
        }
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

}
