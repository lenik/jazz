package net.bodz.bas.validator;

import java.util.regex.Pattern;

import net.bodz.bas.mf.std.AbstractValidator;
import net.bodz.bas.mf.std.ValidationException;
import net.bodz.bas.rtx.IOptions;

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
    public void validate(String string, IOptions options)
            throws ValidationException {
        if (!pattern.matcher(string).matches())
            throw new ValidationException(String.format("String Doesn't match with regex(%s): %s", //
                    pattern.pattern(), string));
    }

}
