package net.bodz.bas.ctx.sys;

import java.text.DateFormat;

public class SystemDateFormatRef
        extends SystemConstantRef<DateFormat> {

    @Override
    public Class<? extends DateFormat> getValueType() {
        return DateFormat.class;
    }

    @Override
    public DateFormat get() {
        return DateFormat.getDateInstance();
    }

}
