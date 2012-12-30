package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class RepeatForListTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public RepeatForListTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
    }

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked") List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();
        Object value = valueTagType.parseJavadoc(tagNameSpec, null, string, negotiation);
        list.add(value);
        return list;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        List<?> list = (List<?>) value;
        for (Object item : list) {
            valueTagType.writeJavadoc(rootTagName, writer, item, negotiation);
        }
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked") List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();

        // suffix -> (suffix-1.suffix-rest)
        int index = Integer.parseInt(suffix);
        while (list.size() <= index)
            list.add(null);

        // Object valueCont = list.get(index);
        Object value = valueTagType.parseEntry(null, null, string, negotiation);
        list.set(index, value);
        return list;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        List<?> list = (List<?>) value;
        for (int index = 0; index < list.size(); index++) {
            Object item = list.get(index);
            if (item != null) {
                valueTagType.writeEntries(out, prefix + "." + index, item, negotiation);
            }
        }
    }

}
