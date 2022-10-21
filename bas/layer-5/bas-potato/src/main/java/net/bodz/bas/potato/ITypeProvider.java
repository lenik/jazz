package net.bodz.bas.potato;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface ITypeProvider
        extends
            IPriority {

    int I_Properties = 0x10000;
    int I_Methods = 0x20000;
    int I_Constructors = 0x40000;
    int I_Events = 0x80000;
    int I_Docs = 0x100000;
    int I_Default = I_Properties | I_Methods | I_Constructors | I_Events | I_Docs;

    int getDefaultInfoset();

    default IType loadType(Class<?> clazz) {
        return loadType(clazz, null, getDefaultInfoset());
    }

    default IType loadType(Class<?> clazz, Object obj) {
        return loadType(clazz, obj, getDefaultInfoset());
    }

    IType loadType(Class<?> clazz, Object obj, int infoset);

}
