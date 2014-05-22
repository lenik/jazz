package net.bodz.bas.fmt.textmap;

import java.util.Iterator;
import java.util.Map.Entry;

import net.bodz.bas.c.java.lang.StringTypers;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom.iStringTypers;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.tools.StreamReading;

/**
 * Recommended file extension: <code>.itm</code>.
 */
public class I18nTextMapParser
        extends TextMapParser<String, iString> {

    public I18nTextMapParser(Iterator<String> lines, int format) {
        this(lines, iStringTypers.getInstance(format));
    }

    public I18nTextMapParser(Iterator<String> lines, iStringTypers typers) {
        super(lines, StringTypers.INSTANCE, typers);
    }

    public static Iterable<Entry<String, iString>> parse(IStreamInputSource src, int format) {
        Iterator<String> iterator = src.to(StreamReading.class).lines().iterator();
        return new I18nTextMapParser(iterator, format);
    }

}
