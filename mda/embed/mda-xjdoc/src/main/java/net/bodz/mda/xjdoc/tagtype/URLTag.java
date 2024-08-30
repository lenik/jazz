package net.bodz.mda.xjdoc.tagtype;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.err.ParseException;

public class URLTag
        extends SimpleDocTag<URL> {

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
    protected String format(URL value) {
        return value.toString();
    }

    static final URLTag instance = new URLTag();

    public static URLTag getInstance() {
        return instance;
    }

}
