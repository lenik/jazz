package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.WordTokenizer;

public abstract class KeyedTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string) {
        String keyText = WordTokenizer.firstWord(string);
        String valueText = string.substring(keyText.length());
        keyText = keyText.trim();
        valueText = valueText.trim();

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(keyText);
        Object valueCont = map.get(key);
        Object value = parseValueJavadoc(valueCont, valueText);
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
            String keyText = formatKey(k);
            String valueText = formatValueJavadoc(v);
            array[index++] = keyText + " " + valueText;
        }
        return array;
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(suffix);
        Object valueCont = map.get(key);
        Object value = parseValueAttribute(valueCont, string);
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
                String keyText = formatKey(k);
                writeValueAttributes(out, prefix + "." + keyText, v);
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

    protected abstract Object parseValueJavadoc(Object cont, String valueString);

    protected abstract String formatValueJavadoc(Object value);

    protected abstract Object parseValueAttribute(Object cont, String string);

    protected abstract void writeValueAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException;

}
