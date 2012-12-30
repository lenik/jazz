package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;

public class TagSpecKeyTagType
        extends AbstractMapCompositeTagType {

    public TagSpecKeyTagType(ITagType valueTagType) {
        super(valueTagType);
    }

    @Override
    public Map<?, ?> parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked") Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(tagNameSpec, negotiation);

        Object valueCont = map.get(key);
        Object value = valueTagType.parseJavadoc(tagNameSpec, valueCont, string, negotiation);
        if (valueCont != value)
            map.put(key, value);

        return map;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object _map, INegotiation negotiation)
            throws FormatException, IOException {
        Map<?, ?> map = (Map<?, ?>) _map;

        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();

            String tagName = rootTagName;

            String keyStr = formatKey(k, negotiation);
            if (keyStr != null && !keyStr.isEmpty())
                tagName += "." + keyStr;

            valueTagType.writeJavadoc(tagName, writer, v, negotiation);
        }
    }

}
