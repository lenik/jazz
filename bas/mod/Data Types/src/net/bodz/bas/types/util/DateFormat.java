package net.bodz.bas.types.util;

import java.text.ParseException;
import java.util.Date;

import net.bodz.bas.proxy.java.util.ProxyDateFormat;

public class DateFormat extends ProxyDateFormat {

    private static final long serialVersionUID = -2505422475486845665L;

    public DateFormat(java.text.DateFormat proxy) {
        super(proxy);
    }

    public Date parse(String source, Date fail) {
        try {
            return super.parse(source);
        } catch (ParseException e) {
            return fail;
        }
    }

    public Object parseObject(String source, Object fail) {
        try {
            return super.parseObject(source);
        } catch (ParseException e) {
            return fail;
        }
    }
    
    

}
