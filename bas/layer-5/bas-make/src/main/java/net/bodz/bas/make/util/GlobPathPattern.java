package net.bodz.bas.make.util;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.make.IKeyPattern;
import net.bodz.bas.meta.decl.NotNull;

public class GlobPathPattern
        implements IKeyPattern<String, Path> {

    static final char STAR = '%';
    static final String STAR_ESC = "%";

    Pattern globPattern;

    public GlobPathPattern(@NotNull Pattern globPattern) {
        this.globPattern = globPattern;
    }

    public GlobPathPattern(@NotNull String globExpr) {
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

    @Override
    public Class<String> getParameterType() {
        return String.class;
    }

    @Override
    public Class<Path> getKeyType() {
        return Path.class;
    }

    @Override
    public String match(Object key) {
        if (!(key instanceof Path))
            return null;

        String str = key.toString();
        Matcher m = globPattern.matcher(str);
        if (!m.matches())
            return null;

        String stem = m.group(1);
        return stem;
    }

}