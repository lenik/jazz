package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFilenameFilter implements FilenameFilter {

    private final boolean whole;
    private final boolean fullpath;
    private final Pattern pattern;

    public RegexFilenameFilter(String regex) {
        this(regex, 0);
    }

    public RegexFilenameFilter(String regex, int flags) {
        this(Pattern.compile(regex, flags), true, false);
    }

    public RegexFilenameFilter(Pattern pattern, boolean whole, boolean fullpath) {
        this.pattern = pattern;
        this.whole = whole;
        this.fullpath = fullpath;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (fullpath)
            name = dir.getPath() + File.separatorChar + name;
        Matcher matcher = pattern.matcher(name);
        if (whole)
            return matcher.matches();
        else
            return matcher.find();
    }

}
