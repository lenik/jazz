package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.text.flatf.IFlatfOutput;

public abstract class RepeatTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();
        Object value = parseJavadocValue(string);
        list.add(value);
        return list;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        List<?> list = (List<?>) value;
        String[] array = new String[list.size()];
        for (int index = 0; index < array.length; index++) {
            Object item = list.get(index);
            String valueText = formatJavadocValue(item);
            array[index] = valueText;
        }
        return array;
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();

        int index = Integer.parseInt(suffix);
        while (list.size() <= index)
            list.add(null);

        // Object valueCont = list.get(index);
        Object value = parseAttributeValue(string);
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
                writeValueAttributes(out, prefix + "." + index, item);
            }
        }
    }

    protected abstract Object parseJavadocValue(String valueString);

    protected abstract String formatJavadocValue(Object value);

    protected abstract Object parseAttributeValue(String valueString);

    protected abstract void writeValueAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException;

}
