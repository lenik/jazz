package net.bodz.bas.doc.io;

import net.bodz.bas.repr.form.meta.NotNull;

public interface IAutoFormat {

    @NotNull
    IDataFormat getDataFormat();

    default String formatData(Object data) {
        IDataFormat df = getDataFormat();
        String str = df.format(data);
        return str;
    }

}
