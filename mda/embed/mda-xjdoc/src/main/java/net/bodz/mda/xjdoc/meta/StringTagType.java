package net.bodz.mda.xjdoc.meta;

import java.io.IOException;

import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string) {
        return string;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        if (value == null)
            return new String[0];
        else
            return new String[] { value.toString() };
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        return string;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        if (!(value instanceof String))
            throw new IllegalArgumentException();
        String string = (String) value;
        out.attribute(prefix, string);
    }

    public static final StringTagType INSTANCE = new StringTagType();

}
