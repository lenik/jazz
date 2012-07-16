package net.bodz.mda.xjdoc.tags;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public class URLTagType
        extends ScalarTagType<URL> {

    @Override
    protected URL parse(String s)
            throws ParseException {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    protected String format(URL value)
            throws FormatException {
        return value.toString();
    }

    static final URLTagType instance = new URLTagType();

    public static URLTagType getInstance() {
        return instance;
    }

}