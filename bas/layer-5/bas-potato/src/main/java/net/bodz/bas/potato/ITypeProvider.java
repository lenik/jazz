package net.bodz.bas.potato;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.mda.xjdoc.model.ClassDoc;

@IndexedType
public interface ITypeProvider
        extends IPriority {

    int PROPERTIES = 0x1;
    int METHODS = 0x2;
    int CONSTRUCTORS = 0x4;
    int EVENTS = 0x8;

    IType getType(Class<?> clazz, Object obj, int infoset, ClassDoc classDoc);

}
