package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class SchemaSubset
        implements
            IJsonForm {

    public String schemaName;

    boolean all;
    public final Set<String> names = new HashSet<>();
    public final Set<Pattern> patterns = new HashSet<>();
    public boolean ignoreCase;

    // boolean allowWildcards = true;

    public SchemaSubset(String schemaName) {
        this.schemaName = schemaName;
    }

    public boolean isAll() {
        return all;
    }

    public boolean isEmpty() {
        if (all)
            return false;
        if (!names.isEmpty())
            return false;
        if (!patterns.isEmpty())
            return false;
        return true;
    }

    public boolean contains(String tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (all)
            return true;
        if (isEmpty())
            return false;
        if (ignoreCase)
            tableName = tableName.toLowerCase();
        if (names.contains(tableName))
            return true;
        for (Pattern pattern : patterns)
            if (pattern.matcher(tableName).matches())
                return true;
        return false;
    }

    public void addAllTables() {
        all = true;
        names.clear();
        patterns.clear();
    }

    public boolean addTableWildcards(String s) {
        if (s.contains("?") || s.contains("*")) {
            String regex = convertWildcardsToRegex(s);
            return addTablePattern(regex);
        } else {
            return addTableName(s);
        }
    }

    static String convertWildcardsToRegex(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 10);
        sb.append("\\Q");
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '?':
                sb.append("\\E.\\Q");
                break;
            case '*':
                sb.append("\\E.*\\Q");
                break;
            default:
                sb.append(ch);
            }
        }
        sb.append("\\E");
        return sb.toString();
    }

    public boolean addTableName(String tableName) {
        if (tableName == null)
            throw new NullPointerException("tableName");
        if (all)
            return false;
        if (ignoreCase)
            tableName = tableName.toLowerCase();
        return names.add(tableName);
    }

    public boolean addTablePattern(String regex) {
        if (regex == null)
            throw new NullPointerException("regex");
        if (all)
            return false;
        if (ignoreCase)
            regex = regex.toLowerCase();
        Pattern pattern = Pattern.compile(regex);
        return patterns.add(pattern);
    }

    @Override
    public String toString() {
        if (all)
            return "\\ALL";
        if (ignoreCase)
            return "i-names: " + names + "\ni-patterns: " + patterns;
        else
            return "names: " + names + "\npatterns: " + patterns;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("schemaName", schemaName);
        out.entry("all", all);
        out.entry("names", Collections.sortCopy(names));
        out.entry("patterns", Collections.sortCopy(patterns, PatternComparator.INSTANCE));
        out.entry("ignoreCase", ignoreCase);
    }

}

class PatternComparator
        extends AbstractNonNullComparator<Pattern> {

    @Override
    public int compareNonNull(Pattern o1, Pattern o2) {
        String p1 = o1.pattern();
        String p2 = o2.pattern();
        return p1.compareTo(p2);
    }

    public static final PatternComparator INSTANCE = new PatternComparator();

}