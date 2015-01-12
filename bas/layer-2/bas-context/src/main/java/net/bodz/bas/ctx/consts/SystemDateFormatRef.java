package net.bodz.bas.ctx.consts;

import java.text.DateFormat;

public class SystemDateFormatRef
        extends SystemConstant<DateFormat> {

    @Override
    public Class<? extends DateFormat> getValueType() {
        return DateFormat.class;
    }

    @Override
    public DateFormat get() {
        return DateFormat.getDateInstance();
    }

}
