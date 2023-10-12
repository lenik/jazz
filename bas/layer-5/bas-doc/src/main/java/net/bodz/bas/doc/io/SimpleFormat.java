package net.bodz.bas.doc.io;

public class SimpleFormat
        implements
            IDataFormat {

    @Override
    public String format(Object data) {
        if (data == null)
            return null;
        else
            return data.toString();
    }

    public static final SimpleFormat INSTANCE = new SimpleFormat();

}
