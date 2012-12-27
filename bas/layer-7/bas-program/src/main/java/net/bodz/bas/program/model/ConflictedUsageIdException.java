package net.bodz.bas.program.model;

import net.bodz.bas.err.IllegalUsageException;

public class ConflictedUsageIdException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    public ConflictedUsageIdException(String usageId, SyntaxUsage existingUsage) {
        super(String.format("Syntax usage id '%s' conflicts with %s: %s.", //
                usageId, //
                existingUsage.getSyntax(), //
                existingUsage.getDescription()));
    }

}
