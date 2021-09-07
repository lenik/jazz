package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.function.Function;
import java.util.regex.Pattern;

public class DoubleBraceExpander
        extends TextPreps.Matches {

    private static Pattern contentPattern;
    static {
        // RegExp: \{\{(.*?)\}\}
        contentPattern = Pattern.compile( //
                "\\{\\{(.*?)\\}\\}");
    }

    private Function<String, ?> fn;

    public DoubleBraceExpander(Function<String, ?> convFn) {
        super(contentPattern);
        if (convFn == null)
            throw new NullPointerException("convFn");
        this.fn = convFn;
    }

    protected String expand(String name) {
        if (fn != null) {
            Object val = fn.apply(name);
            if (val != null)
                return String.valueOf(val);
        }
        return null;
    }

    @Override
    protected void matched(CharSequence in, int start, int end, Appendable out)
            throws IOException {
        String content = matcher.group(1);
        String expanded = expand(content);
        out.append(expanded);
    }

}
