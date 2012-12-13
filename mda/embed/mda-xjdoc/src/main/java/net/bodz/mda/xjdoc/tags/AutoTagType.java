package net.bodz.mda.xjdoc.tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class AutoTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation)
            throws ParseException {
        return null;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation) {
        return null;
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        return null;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws IOException {

        if (value == null)
            return;

        else if (value instanceof String) {
            out.attribute(prefix, (String) value);
        }

        else if (value instanceof DomainString) {
            out.attribute(prefix, (DomainString) value);
        }

        else if (value instanceof List<?>) {
            List<?> list = (List<?>) value;
            for (int index = 0; index < list.size(); index++)
                writeEntries(out, prefix + "." + index, list.get(index), negotiation);
        }

        else if (value instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) value;
            for (Entry<?, ?> entry : map.entrySet()) {
                Object itemKey = entry.getKey();
                Object itemValue = entry.getValue();
                writeEntries(out, prefix + "." + itemKey, itemValue, negotiation);
            }
        }

        else
            throw new IllegalUsageException("Unsupported tag value type: " + value.getClass());
    }

}
