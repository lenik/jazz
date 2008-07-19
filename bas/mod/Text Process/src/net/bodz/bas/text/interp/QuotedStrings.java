package net.bodz.bas.text.interp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.bodz.bas.lang.Filter;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.util.PatternProcessor;

public abstract class QuotedStrings extends PatternProcessor {

    private int quoteCharLen;

    public QuotedStrings(String quoteChar) {
        super(compile(quoteChar));
        quoteCharLen = quoteChar.length();
    }

    private static Pattern compile(String quoteChar) {
        String qq = Pattern.quote(quoteChar);
        return Pattern.compile(qq + "(\\\\.|.)*?" + qq);
    }

    @Override
    protected final void matched(String part) {
        String inner = part.substring(quoteCharLen, part.length()
                - quoteCharLen);
        quoted(inner);
    }

    protected String dequote(String text) {
        return Unescape.interp(text);
    }

    protected void quoted(String text) {
        String dequoted = dequote(text);
        part(dequoted, true);
    }

    protected void unquoted(String text) {
        part(text, false);
    }

    protected abstract void part(String text, boolean quoted);

    public static String[] split(String s, String quoteChar,
            final Filter<String, String> dequote) {
        final List<String> list = new ArrayList<String>();
        QuotedStrings qs = new QuotedStrings(quoteChar) {
            @Override
            protected String dequote(String s) {
                if (dequote == null)
                    return super.dequote(s);
                return dequote.filter(s);
            }

            @Override
            protected void part(String text, boolean quoted) {
                throw new NotImplementedException();
            }
        };
        qs.process(s);
        return list.toArray(new String[0]);
    }

}
