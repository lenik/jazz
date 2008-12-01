package net.bodz.bas.proxy.java.util;

import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ProxyDateFormat extends DateFormat {

    private static final long serialVersionUID = 6935840126102525189L;

    public final DateFormat   proxy;

    public ProxyDateFormat(DateFormat proxy) {
        assert proxy != null;
        this.proxy = proxy;
    }

    @Override
    public Object clone() {
        return proxy.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return proxy.equals(obj);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo,
            FieldPosition fieldPosition) {
        return proxy.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return proxy.formatToCharacterIterator(obj);
    }

    @Override
    public Calendar getCalendar() {
        return proxy.getCalendar();
    }

    @Override
    public NumberFormat getNumberFormat() {
        return proxy.getNumberFormat();
    }

    @Override
    public TimeZone getTimeZone() {
        return proxy.getTimeZone();
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

    @Override
    public boolean isLenient() {
        return proxy.isLenient();
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return proxy.parse(source, pos);
    }

    @Override
    public Date parse(String source) throws ParseException {
        return proxy.parse(source);
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        return proxy.parseObject(source, pos);
    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return proxy.parseObject(source);
    }

    @Override
    public void setCalendar(Calendar newCalendar) {
        proxy.setCalendar(newCalendar);
    }

    @Override
    public void setLenient(boolean lenient) {
        proxy.setLenient(lenient);
    }

    @Override
    public void setNumberFormat(NumberFormat newNumberFormat) {
        proxy.setNumberFormat(newNumberFormat);
    }

    @Override
    public void setTimeZone(TimeZone zone) {
        proxy.setTimeZone(zone);
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

}
