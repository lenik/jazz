package net.bodz.bas.validator;

import java.util.Collection;

import net.bodz.bas.tf.std.AbstractValidator;
import net.bodz.bas.tf.std.ValidationException;
import net.bodz.bas.rtx.IOptions;

/**
 * One and only one in the set is allowed to be set.
 */
public class OneOfValidator
        extends AbstractValidator<Collection<?>> {

    @Override
    public void validate(Collection<?> collection, IOptions options)
            throws ValidationException {
        int index = 0;
        int lastNonNullIndex = -1;
        for (Object item : collection) {
            if (item != null) {
                if (lastNonNullIndex != -1)
                    throw new ValidationException(String.format("Only one is allowed: lastIndex=%d, index=%d",//
                            lastNonNullIndex, index));
                lastNonNullIndex = index;
            }
            index++;
        }
        if (lastNonNullIndex == -1)
            throw new ValidationException("None is specified.");
    }

}
