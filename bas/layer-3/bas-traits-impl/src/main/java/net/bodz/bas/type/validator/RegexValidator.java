package net.bodz.bas.type.validator;

import java.util.regex.Pattern;

import net.bodz.bas.type.traits.AbstractValidator;
import net.bodz.bas.type.traits.ValidateException;

public class RegexValidator
        extends AbstractValidator<String> {

    private final Pattern pattern;

    public RegexValidator(Pattern pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
    }

    public RegexValidator(String regex) {
        if (regex == null)
            throw new NullPointerException("regex");
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void validate(String string)
            throws ValidateException {
        if (!pattern.matcher(string).matches())
            throw new ValidateException(String.format("String Doesn't match with regex(%s): %s", //
                    pattern.pattern(), string));
    }

}
