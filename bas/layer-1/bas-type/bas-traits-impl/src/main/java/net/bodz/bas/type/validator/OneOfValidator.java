package net.bodz.bas.type.validator;

import java.util.Collection;

import net.bodz.bas.type.traits.AbstractValidator;
import net.bodz.bas.type.traits.ValidateException;

/**
 * One and only one in the set is allowed to be set.
 */
public class OneOfValidator
        extends AbstractValidator<Collection<?>> {

    @Override
    public void validate(Collection<?> collection)
            throws ValidateException {
        int index = 0;
        int lastNonNullIndex = -1;
        for (Object item : collection) {
            if (item != null) {
                if (lastNonNullIndex != -1)
                    throw new ValidateException(String.format("Only one is allowed: lastIndex=%d, index=%d",//
                            lastNonNullIndex, index));
                lastNonNullIndex = index;
            }
            index++;
        }
        if (lastNonNullIndex == -1)
            throw new ValidateException("None is specified.");
    }

}
