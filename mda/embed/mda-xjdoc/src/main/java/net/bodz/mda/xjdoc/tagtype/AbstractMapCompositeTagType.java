package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public abstract class AbstractMapCompositeTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public AbstractMapCompositeTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
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

    @Override
    public Map<?, ?> parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked") Map<Object, Object> map = (Map<Object, Object>) cont;
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

}