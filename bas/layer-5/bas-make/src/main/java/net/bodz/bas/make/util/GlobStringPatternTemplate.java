package net.bodz.bas.make.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.meta.decl.NotNull;

public abstract class GlobStringPatternTemplate<T>
        implements IDataTypedKeyPattern<String, String, T> {

    static final char STAR = '%';
    static final String STAR_ESC = "%";

    final Pattern globPattern;

    public GlobStringPatternTemplate(@NotNull Pattern globPattern) {
        this.globPattern = globPattern;
    }

    public GlobStringPatternTemplate(@NotNull String globExpr) {
        int count = 0;
        int pos = 0;
        while (pos < globExpr.length()) {
            int star = globExpr.indexOf(STAR, pos);
            if (star == -1)
                break;
            count++;
            pos = star + 1;
        }
        if (count != 1)
            throw new IllegalArgumentException("must be exact one star(*) in the glob expr: " + globExpr);

        String regex = globExpr//
                .replace(STAR_ESC, "(.*)");
        this.globPattern = Pattern.compile("^" + regex + "$");
    }

    @NotNull
    @Override
    public Class<String> getParameterType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<String> getKeyType() {
        return String.class;
    }

    @Override
    public String match(Object key) {
        if (!(key instanceof String))
            return null;

        String stringKey = key.toString();
        Matcher m = globPattern.matcher(stringKey);
        if (!m.matches())
            return null;

        String stem = m.group(1);
        return stem;
    }

}