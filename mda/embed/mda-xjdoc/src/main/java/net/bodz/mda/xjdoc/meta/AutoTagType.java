package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.free.IllegalUsageException;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class AutoTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string) {
        return null;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        return null;
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        return null;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
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
                writeAttributes(out, prefix + "." + index, list.get(index));
        }

        else if (value instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) value;
            for (Entry<?, ?> entry : map.entrySet()) {
                Object itemKey = entry.getKey();
                Object itemValue = entry.getValue();
                writeAttributes(out, prefix + "." + itemKey, itemValue);
            }
        }

        else
            throw new IllegalUsageException("Unsupported tag value type: " + value.getClass());
    }

}
