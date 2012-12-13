package net.bodz.bas.potato;

import net.bodz.bas.potato.model.IType;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.Traits;

public class Potatoes {

    public static IType getType(Class<?> objType)
            throws QueryException {
        return Traits.getTrait(objType, IType.class);
    }

}
