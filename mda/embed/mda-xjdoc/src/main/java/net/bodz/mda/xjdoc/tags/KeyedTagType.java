package net.bodz.mda.xjdoc.tags;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.util.WordTokenizer;

/**
 * Keyed-Tag: The first word in the text is treated as the key for the tag.
 */
public class KeyedTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public KeyedTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
    }

    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation)
            throws ParseException {
        String keyStr = WordTokenizer.firstWord(string);
        String valueStr = string.substring(keyStr.length());
        keyStr = keyStr.trim();
        valueStr = valueStr.trim();

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(keyStr, negotiation);
        Object valueCont = map.get(key);
        Object value = valueTagType.parseJavadoc(valueCont, valueStr, negotiation);
        if (valueCont != value)
            map.put(key, value);
        return map;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation)
            throws FormatException {
        Map<?, ?> map = (Map<?, ?>) value;
        String[] array = new String[map.size()];

        int index = 0;
        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            String keyStr = formatKey(k, negotiation);
            for (String valueStr : valueTagType.formatJavadoc(v, negotiation))
                array[index] = keyStr + " " + valueStr;
            index++;
        }
        return array;
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        // suffix -> suffix-1.suffix-rest
        Object key = parseKey(suffix, negotiation);
        Object valueCont = map.get(key);
        Object value = valueTagType.parseEntry(valueCont, null, string, negotiation);
        // return new Pair<Object, Object>(key, value);
        if (valueCont == null)
            map.put(key, value);
        return map;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        Map<?, ?> map = (Map<?, ?>) value;
        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            if (v != null) {
                String keyStr = formatKey(k, negotiation);
                valueTagType.writeEntries(out, prefix + "." + keyStr, v, negotiation);
            }
        }
    }

    protected Object parseKey(String keyText, INegotiation negotiation) {
        return keyText;
    }

    protected String formatKey(Object key, INegotiation negotiation) {
        if (key == null)
            return null;
        else
            return key.toString();
    }

}
