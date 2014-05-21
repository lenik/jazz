package net.bodz.bas.context.ref;

import java.text.DateFormat;

import net.bodz.bas.t.ref.ReadOnlyRef;

public class SystemDateFormatRef
        extends ReadOnlyRef<DateFormat> {

    @Override
    public Class<? extends DateFormat> getValueType() {
        return DateFormat.class;
    }

    @Override
    public DateFormat get() {
        return DateFormat.getDateInstance();
    }

}
