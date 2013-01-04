package net.bodz.bas.potato;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.mda.xjdoc.model.ClassDoc;

@IndexedType
public interface ITypeProvider
        extends IPriority {

    int PROPERTIES = 0x1_0000;
    int METHODS = 0x2_0000;
    int CONSTRUCTORS = 0x4_0000;
    int EVENTS = 0x8_0000;

    IType getType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc);

}
