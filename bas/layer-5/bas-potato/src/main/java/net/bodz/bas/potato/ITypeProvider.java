package net.bodz.bas.potato;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface ITypeProvider
        extends IPriority {

    int I_Properties = 0x1_0000;
    int I_Methods = 0x2_0000;
    int I_Constructors = 0x4_0000;
    int I_Events = 0x8_0000;
    int I_Docs = 0x10_0000;
    int I_Default = I_Properties | I_Methods | I_Constructors | I_Events | I_Docs;

    int getDefaultInfoset();

    IType loadType(Class<?> clazz);

    IType loadType(Class<?> clazz, Object obj);

    IType loadType(Class<?> clazz, Object obj, int infoset);

}
