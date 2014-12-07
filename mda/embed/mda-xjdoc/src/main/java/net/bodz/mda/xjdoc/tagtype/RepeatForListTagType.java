package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;

public class RepeatForListTagType
        extends AbstractTagType {

    final ITagType valueTagType;

    public RepeatForListTagType(ITagType valueTagType) {
        if (valueTagType == null)
            throw new NullPointerException("valueTagType");
        this.valueTagType = valueTagType;
    }

    @Override
    public Class<?> getValueType() {
        return List.class;
    }

    @Override
    public List<Object> parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options)
            throws ParseException {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();
        Object value = valueTagType.parseJavadoc(tagNameSpec, null, string, options);
        list.add(value);
        return list;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, IOptions options)
            throws IOException {
        List<?> list = (List<?>) value;
        for (Object item : list) {
            valueTagType.writeJavadoc(rootTagName, writer, item, options);
        }
    }

    @Override
    public List<Object> parseEntry(Object cont, String suffix, String string, IOptions options)
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
        Object value = valueTagType.parseEntry(null, null, string, options);
        list.set(index, value);
        return list;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
        List<?> list = (List<?>) value;
        for (int index = 0; index < list.size(); index++) {
            Object item = list.get(index);
            if (item != null) {
                valueTagType.writeEntries(out, prefix + "." + index, item, options);
            }
        }
    }

}
