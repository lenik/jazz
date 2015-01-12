package net.bodz.bas.ctx.inject;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IBeanResolver
        extends IPriority {

    boolean contains(String name);

    Object resolve(String name);

}
