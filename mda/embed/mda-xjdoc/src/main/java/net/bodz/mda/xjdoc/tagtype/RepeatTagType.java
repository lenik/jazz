package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

class RepeatTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public RepeatTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
    }

    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();
        Object value = valueTagType.parseJavadoc(null, string, negotiation);
        list.add(value);
        return list;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation)
            throws FormatException {
        List<?> list = (List<?>) value;
        String[] array = new String[list.size()];
        for (int index = 0; index < array.length; index++) {
            Object item = list.get(index);
            for (String itemJavadoc : valueTagType.formatJavadoc(item, negotiation))
                array[index] = itemJavadoc;
        }
        return array;
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
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
