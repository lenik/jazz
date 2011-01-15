package net.bodz.bas.type.java.util.regex;

import java.util.regex.Pattern;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;
import net.bodz.bas.lang.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.util.exception.ParseException;

public class PatternTraits
        extends AbstractCommonTraits<Pattern> {

    /**
     * The pattern mode in use.
     * 
     * @see #javaTextformMode
     * @see #globTextformMode
     */
    @ValueType(String.class)
    public static final String textformMode = "textform.mode";
    public static final String javaTextformMode = "java";
    public static final String globTextformMode = "glob";
    public static final String defaultTextformMode = javaTextformMode;

    /**
     * The flags passed to {@link Pattern#compile(String, int)}.
     */
    @ValueType(Integer.class)
    public static final String regexFlags = "regex.flags";
    public static final int defeaultRegexFlags = 0;

    public PatternTraits() {
        super(Pattern.class);
    }

    @Override
    public Pattern parse(String regexp)
            throws ParseException {
        return Pattern.compile(regexp);
    }

    @Override
    public Pattern parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        String mode = defaultTextformMode;
        int flags = defeaultRegexFlags;

        NegotiationParameter modeParam = null;

        for (NegotiationParameter param : negotiation) {
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
