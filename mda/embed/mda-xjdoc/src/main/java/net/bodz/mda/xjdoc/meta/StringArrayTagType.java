package net.bodz.mda.xjdoc.meta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringArrayTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string) {
        @SuppressWarnings("unchecked")
        List<String> arrayList = (List<String>) cont;
        if (arrayList == null)
            arrayList = new ArrayList<String>();
        arrayList.add(string);
        return arrayList;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        if (value == null)
            return new String[0];

        @SuppressWarnings("unchecked")
        List<String> arrayList = (List<String>) value;

        return arrayList.toArray(new String[0]);
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        @SuppressWarnings("unchecked")
        List<String> arrayList = (List<String>) cont;
        if (arrayList == null)
            arrayList = new ArrayList<String>();

        int index = Integer.valueOf(suffix);
        while (arrayList.size() <= index)
            arrayList.add(null);

        arrayList.set(index, string);
        return arrayList;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        @SuppressWarnings("unchecked")
        List<String> arrayList = (List<String>) value;
        for (int index = 0; index < arrayList.size(); index++) {
            String item = arrayList.get(index);
            out.attribute(prefix + "." + index, item);
        }
    }

    public static final StringArrayTagType INSTANCE = new StringArrayTagType();

}
