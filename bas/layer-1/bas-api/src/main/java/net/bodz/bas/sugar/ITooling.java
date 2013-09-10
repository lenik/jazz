package net.bodz.bas.sugar;

import net.bodz.bas.t.object.IContextUtility;

public interface ITooling
        extends IContextUtility {

    <T> T _for(Class<T> toolsType);

}
