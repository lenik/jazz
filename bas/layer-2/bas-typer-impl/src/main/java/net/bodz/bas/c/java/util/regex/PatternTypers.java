package net.bodz.bas.c.java.util.regex;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IllegalParameterUsageException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class PatternTypers
        extends AbstractCommonTypers<Pattern> {

    /**
     * The pattern mode in use.
     * 
     * @see #javaTextformMode
     * @see #globTextformMode
     */
    @ParameterType(String.class)
    public static final String textformMode = "textform.mode";
    public static final String javaTextformMode = "java";
    public static final String globTextformMode = "glob";
    public static final String defaultTextformMode = javaTextformMode;

    /**
     * The flags passed to {@link Pattern#compile(String, int)}.
     */
    @ParameterType(Integer.class)
    public static final String regexFlags = "regex.flags";
    public static final int defeaultRegexFlags = 0;

    public PatternTypers() {
        super(Pattern.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public Pattern parse(String regexp)
            throws ParseException {
        return Pattern.compile(regexp);
    }

    @Override
    public Pattern parse(String text, IOptions options)
            throws ParseException {
        String mode = options.get(textformMode, defaultTextformMode);
        int flags = options.getInt(regexFlags, defeaultRegexFlags);

        if (javaTextformMode.equals(mode))
            return Pattern.compile(text, flags);
        else if (globTextformMode.equals(mode)) {
            String regex = Pattern.quote(text);
            regex = regex.replace("*", "\\E.*\\Q");
            regex = regex.replace("?", "\\E.\\Q");
            return Pattern.compile(regex, flags);
        } else
            throw new IllegalParameterUsageException(options.getOption(textformMode));
    }

}
