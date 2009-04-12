package net.bodz.bas.text.interp;

import java.util.Map;
import java.util.regex.Pattern;

public class VariableExpand extends PatternProcessor {

    private static Pattern variableRefPattern;
    static {
        variableRefPattern = Pattern.compile( //
                "\\$(?:(\\w+)|\\{((?:\\\\.|.)*?)\\})"); //$NON-NLS-1$
    }

    private Map<String, ?> map;

    public VariableExpand() {
        super(variableRefPattern);
    }

    public VariableExpand(Map<String, ?> map) {
        super(variableRefPattern);
        this.map = map;
    }

    protected String unescapeBracedName(String name) {
        return Unescape.unescape(name);
    }

    protected String expand(String name) {
        if (map != null && map.containsKey(name)) {
            Object val = map.get(name);
            return String.valueOf(val);
        }
        return null;
    }

    @Override
    protected void matched(int start, int end) {
        String name = matcher.group(1);
        if (name == null) {
            name = unescapeBracedName(matcher.group(2));
        }
        assert name != null;
        String expanded = expand(name);
        if (expanded == null)
            undefined(name, start, end);
        else
            defined(name, expanded);
    }

    protected void defined(String name, String expanded) {
        print(expanded);
    }

    protected void undefined(String name, int start, int end) {
        super.unmatched(start, end);
    }

}
