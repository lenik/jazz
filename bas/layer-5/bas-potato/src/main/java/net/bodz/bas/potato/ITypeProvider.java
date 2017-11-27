package net.bodz.bas.potato;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface ITypeProvider
        extends IPriority {

    int PROPERTIES = 0x1_0000;
    int METHODS = 0x2_0000;
    int CONSTRUCTORS = 0x4_0000;
    int EVENTS = 0x8_0000;
    int DOCS = 0x10_0000;

    IType loadType(Class<?> clazz);

    IType loadType(Class<?> clazz, Object obj);

    IType loadType(Class<?> clazz, Object obj, int infoset);

}
