package net.bodz.mda.xjdoc;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * Service provider interface.
 * 
 * @see ClassDocLoader
 */
@IndexedType
public interface IClassDocLoader
        extends IPriority {

    ClassDoc load(Class<?> clazz)
            throws ClassDocLoadException;

}
