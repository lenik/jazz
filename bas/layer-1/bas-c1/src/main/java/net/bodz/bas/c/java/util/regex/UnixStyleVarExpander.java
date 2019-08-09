package net.bodz.bas.c.java.util.regex;

import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.MapGetTransformer;
import net.bodz.bas.fn.ITransformer;

public class UnixStyleVarExpander
        extends TextPrepByParts.Matches {

    private static Pattern variableRefPattern;
    static {
        variableRefPattern = Pattern.compile( //
                "\\$(?:(\\w+)|\\{((?:\\\\.|.)*?)\\})");
    }

    private ITransformer<String, ?> fn;

    public UnixStyleVarExpander() {
        super(variableRefPattern);
    }

    public UnixStyleVarExpander(ITransformer<String, ?> varfn) {
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
            Object val = fn.transform(name);
            if (val != null)
                return String.valueOf(val);
        }
        return null;
    }

    @Override
    protected String matched(String part) {
        String name = matcher.group(1);
        if (name == null) {
            name = unescapeBracedName(matcher.group(2));
        }
        assert name != null;
        String expanded = expand(name);
        if (expanded != null)
            return defined(name, expanded);
        else
            return undefined(name, part);
    }

    protected String defined(String name, String expanded) {
        return expanded;
    }

    protected String undefined(String name, String content) {
        return content;
    }

}
