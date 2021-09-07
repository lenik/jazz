package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.MapGetTransformer;

public class UnixStyleVarExpander
        extends TextPreps.Matches {

    private static Pattern variableRefPattern;
    static {
        // RegExp: \$ (?:([0-9]+|[a-zA-Z_][a-zA-Z_0-9]*) | \{((?:\\.|.)*?)\})
        variableRefPattern = Pattern.compile( //
                "\\$(?:([0-9]+|[a-zA-Z_][a-zA-Z_0-9]*)|\\{((?:\\\\.|.)*?)\\})");
    }

    private Function<String, ?> fn;

    public UnixStyleVarExpander() {
        super(variableRefPattern);
    }

    public UnixStyleVarExpander(Function<String, ?> varfn) {
        super(variableRefPattern);
        this.fn = varfn;
    }

    public UnixStyleVarExpander(Map<?, ?> map) {
        super(variableRefPattern);
        this.fn = new MapGetTransformer<String, Object>(map);
    }

    protected String unescapeBracedName(String name) {
        return Unescape.unescape(name);
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
        String name = matcher.group(1);
        if (name == null) {
            name = unescapeBracedName(matcher.group(2));
        }
        assert name != null;
        String expanded = expand(name);
        if (expanded != null)
            defined(out, name, expanded);
        else
            undefined(in, start, end, out, name);
    }

    protected void defined(Appendable out, String name, String value)
            throws IOException {
        out.append(value);
    }

    protected void undefined(CharSequence in, int start, int end, Appendable out, String name)
            throws IOException {
        out.append(in, start, end);
    }

}
