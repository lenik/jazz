package net.bodz.bas.program.model;

import net.bodz.bas.err.IllegalUsageException;

public class ConflictedOptionKeyException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    public ConflictedOptionKeyException(String optionKey, IOption existingOption) {
        super(String.format("Option name '%s' conflicts with %s: %s.", //
                optionKey, //
                existingOption.getDisplayName(), //
                existingOption.getDescription()));
    }

}
