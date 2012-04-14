package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public Object parseJavadoc(Object cont, String string) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();
        Object value = valueTagType.parseJavadoc(null, string);
        list.add(value);
        return list;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        List<?> list = (List<?>) value;
        String[] array = new String[list.size()];
        for (int index = 0; index < array.length; index++) {
            Object item = list.get(index);
            for (String itemJavadoc : valueTagType.formatJavadoc(item))
                array[index] = itemJavadoc;
        }
        return array;
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();

        // suffix -> (suffix-1.suffix-rest)
        int index = Integer.parseInt(suffix);
        while (list.size() <= index)
            list.add(null);

        // Object valueCont = list.get(index);
        Object value = valueTagType.parseAttribute(null, null, string);
        list.set(index, value);
        return list;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        List<?> list = (List<?>) value;
        for (int index = 0; index < list.size(); index++) {
            Object item = list.get(index);
            if (item != null) {
                valueTagType.writeAttributes(out, prefix + "." + index, item);
            }
        }
    }

}
