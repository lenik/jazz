package net.bodz.bas.potato;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.traits.IType;
import net.bodz.bas.traits.Traits;

public class Potatoes {

    public static IType getType(Class<?> objType)
            throws QueryException {
        return Traits.getTraits(objType, IType.class);
    }

}
