package net.bodz.bas.c.java.util.regex;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;

public class PatternTraits
        extends AbstractCommonTraits<Pattern> {

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

    public PatternTraits() {
        super(Pattern.class);
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
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
    public Pattern parse(String text, INegotiation negotiation)
            throws ParseException {
        String mode = defaultTextformMode;
        int flags = defeaultRegexFlags;

        IParameter modeParam = null;

        for (IParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (textformMode.equals(paramId)) {
                mode = (String) paramValue;
                modeParam = param;
            } else if (regexFlags.equals(paramId))
                flags = (Integer) paramValue;
        }

        if (javaTextformMode.equals(mode))
            return Pattern.compile(text, flags);
        else if (globTextformMode.equals(mode)) {
            String regex = Pattern.quote(text);
            regex = regex.replace("*", "\\E.*\\Q");
            regex = regex.replace("?", "\\E.\\Q");
            return Pattern.compile(regex, flags);
        } else
            throw new NegotiationException(modeParam);
    }

}
