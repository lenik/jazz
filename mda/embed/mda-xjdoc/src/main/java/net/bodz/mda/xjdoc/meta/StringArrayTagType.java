package net.bodz.mda.xjdoc.meta;

public class StringArrayTagType
        extends AbstractTagType {

    @Override
    public Object parse(Object cont, String string) {
        if (cont == null)
            return new String[] { string };
        else {
            String[] prev = (String[]) cont;
            String[] join = new String[prev.length + 1];
            System.arraycopy(prev, 0, join, 0, prev.length);
            join[prev.length] = string;
            return join;
        }
    }

    @Override
    public String[] format(Object value) {
        if (value == null)
            return new String[0];
        else
            return (String[]) value;
    }

}
