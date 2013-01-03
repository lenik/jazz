package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.mda.xjdoc.util.WordTokenizer;

/**
 * Keyed-Tag: The first word in the text is treated as the key for the tag.
 */
public class FirstWordKeyTagType
        extends AbstractMapCompositeTagType {

    public FirstWordKeyTagType(ITagType valueTagType) {
        super(valueTagType);
    }

    @Override
    public Map<?, ?> parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        String keyStr = WordTokenizer.firstWord(string);
        String valueStr = string.substring(keyStr.length());
        keyStr = keyStr.trim();
        valueStr = valueStr.trim();

        @SuppressWarnings("unchecked") Map<Object, Object> map = (Map<Object, Object>) cont;
        if (map == null)
            map = new LinkedHashMap<Object, Object>();

        Object key = parseKey(keyStr, negotiation);

        Object valueCont = map.get(key);
        Object value = valueTagType.parseJavadoc(tagNameSpec, valueCont, valueStr, negotiation);
        if (valueCont != value)
            map.put(key, value);

        return map;
    }

    @Override
    public void writeJavadoc(String rootTagName, final IJavadocWriter writer, Object _map, INegotiation negotiation)
            throws FormatException, IOException {
        Map<?, ?> map = (Map<?, ?>) _map;

        for (Entry<?, ?> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();

            final String keyStr = formatKey(k, negotiation);

            IJavadocWriter prependKeyWriter = new IJavadocWriter() {
                @Override
                public void writeTag(String tagName, String string)
                        throws IOException {
                    writer.writeTag(tagName, keyStr + " " + string);
                }
            };

            valueTagType.writeJavadoc(rootTagName, prependKeyWriter, v, negotiation);
        }
    }

}