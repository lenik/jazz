package net.bodz.bas.t.variant.conv;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;

public class DateVarConverterTest
        extends Assert {

    static DateVarConverter converter = DateVarConverter.INSTANCE;

    public static void main(String[] args)
            throws ParseException {
        Date date = converter.fromString("2013-04-11T11:22:33.123456+08:00");
        System.out.println(date);
    }

}
